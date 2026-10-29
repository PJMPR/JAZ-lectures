package pl.pjatk.jaz.annotations.objectbuilder.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Map;

import pl.pjatk.jaz.annotations.objectbuilder.annotation.BuildConstructor;
import pl.pjatk.jaz.annotations.objectbuilder.annotation.Buildable;
import pl.pjatk.jaz.annotations.objectbuilder.annotation.ConfigValue;
import pl.pjatk.jaz.annotations.objectbuilder.annotation.PostBuild;

/**
 * Edukacyjny builder tworzący obiekty na podstawie adnotacji i refleksji.
 *
 * <p>Kolejność pracy:</p>
 * <ol>
 *   <li>sprawdzenie adnotacji na klasie,</li>
 *   <li>odnalezienie oznaczonego konstruktora,</li>
 *   <li>utworzenie argumentów z adnotacji parametrów,</li>
 *   <li>utworzenie instancji,</li>
 *   <li>uzupełnienie oznaczonych pól,</li>
 *   <li>wywołanie oznaczonej metody kończącej inicjalizację.</li>
 * </ol>
 *
 * <p>Przykład świadomie obsługuje tylko kilka prostych typów. Produkcyjny
 * mechanizm potrzebowałby rozszerzalnych konwerterów, obsługi dziedziczenia,
 * lepszego modelu wartości domyślnych i dokładniejszej kontroli dostępu.</p>
 */
public final class ObjectBuilder {
    private final Map<String, String> configuration;

    /**
     * @param configuration tekstowe wartości identyfikowane przez klucze
     *                      używane w {@link ConfigValue}
     */
    public ObjectBuilder(Map<String, String> configuration) {
        this.configuration = Map.copyOf(configuration);
    }

    /**
     * Buduje instancję wskazanego typu.
     *
     * <p>{@code Class<T>} przekazuje do runtime informację o klasie, a parametr
     * typu {@code T} pozwala zachować jej typ w wyniku metody.</p>
     */
    public <T> T build(Class<T> type) {
        requireBuildable(type);
        Constructor<T> constructor = findBuildConstructor(type);
        Object[] arguments = resolveConstructorArguments(constructor);
        T instance = instantiate(constructor, arguments);
        injectFields(instance, type);
        invokePostBuildMethods(instance, type);
        return instance;
    }

    private void requireBuildable(Class<?> type) {
        // isAnnotationPresent sprawdza, czy klasa ma adnotację danego typu.
        if (!type.isAnnotationPresent(Buildable.class)) {
            throw new ObjectBuildException("Brak @Buildable na klasie " + type.getName());
        }
    }

    private <T> Constructor<T> findBuildConstructor(Class<T> type) {
        // getDeclaredConstructors zwraca także konstruktory niepubliczne.
        // Strumień filtruje je według obecności adnotacji @BuildConstructor.
        Constructor<?>[] matches = Arrays.stream(type.getDeclaredConstructors())
            .filter(constructor -> constructor.isAnnotationPresent(BuildConstructor.class))
            .toArray(Constructor[]::new);

        if (matches.length != 1) {
            throw new ObjectBuildException(
                "Klasa musi mieć dokładnie jeden @BuildConstructor: " + type.getName());
        }

        // Sprawdziliśmy, że Constructor pochodzi z Class<T>. Rzutowanie jest
        // potrzebne, ponieważ getDeclaredConstructors zwraca Constructor<?>[].
        @SuppressWarnings("unchecked")
        Constructor<T> constructor = (Constructor<T>) matches[0];
        constructor.trySetAccessible();
        return constructor;
    }

    private Object[] resolveConstructorArguments(Constructor<?> constructor) {
        // Parameter opisuje pojedynczy parametr deklaracji konstruktora.
        // Kolejność tablicy musi odpowiadać kolejności argumentów newInstance.
        return Arrays.stream(constructor.getParameters())
            .map(this::resolveParameter)
            .toArray();
    }

    private Object resolveParameter(Parameter parameter) {
        // getAnnotation zwraca obiekt adnotacji albo null. requireAnnotation
        // wyraża kontrakt buildera: każdy parametr musi mieć @ConfigValue.
        ConfigValue annotation = parameter.getAnnotation(ConfigValue.class);
        if (annotation == null) {
            throw new ObjectBuildException(
                "Brak @ConfigValue na parametrze " + parameter);
        }
        String rawValue = findRawValue(annotation);
        return convert(rawValue, parameter.getType());
    }

    private <T> T instantiate(Constructor<T> constructor, Object[] arguments) {
        try {
            // newInstance wykonuje konstruktor refleksyjnie. JVM sprawdza liczbę
            // i typy argumentów dopiero podczas działania programu.
            return constructor.newInstance(arguments);
        } catch (InstantiationException | IllegalAccessException |
                 InvocationTargetException | IllegalArgumentException exception) {
            throw new ObjectBuildException("Nie można utworzyć obiektu", exception);
        }
    }

    private void injectFields(Object instance, Class<?> type) {
        for (Field field : type.getDeclaredFields()) {
            ConfigValue annotation = field.getAnnotation(ConfigValue.class);
            if (annotation == null) {
                continue;
            }

            String rawValue = findRawValue(annotation);
            Object converted = convert(rawValue, field.getType());
            try {
                // Pole jest prywatne, dlatego przykład próbuje otworzyć dostęp.
                // W aplikacji modułowej dostęp może wymagać dyrektywy opens.
                if (!field.trySetAccessible()) {
                    throw new ObjectBuildException("Brak dostępu do pola " + field);
                }
                field.set(instance, converted);
            } catch (IllegalAccessException exception) {
                throw new ObjectBuildException("Nie można ustawić pola " + field, exception);
            }
        }
    }

    private void invokePostBuildMethods(Object instance, Class<?> type) {
        for (Method method : type.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(PostBuild.class)) {
                continue;
            }
            validatePostBuild(method);
            try {
                if (!method.trySetAccessible()) {
                    throw new ObjectBuildException("Brak dostępu do metody " + method);
                }
                // invoke wywołuje metodę na konkretnej instancji. Nie przekazuje
                // argumentów, ponieważ kontrakt @PostBuild ich zabrania.
                method.invoke(instance);
            } catch (IllegalAccessException | InvocationTargetException exception) {
                throw new ObjectBuildException("Nie można wykonać " + method, exception);
            }
        }
    }

    private void validatePostBuild(Method method) {
        if (method.getParameterCount() != 0 || method.getReturnType() != void.class) {
            throw new ObjectBuildException(
                "@PostBuild wymaga metody void bez parametrów: " + method);
        }
    }

    private String findRawValue(ConfigValue annotation) {
        // value() i defaultValue() są elementami obiektu adnotacji.
        String configured = configuration.get(annotation.value());
        if (configured != null) {
            return configured;
        }
        if (!annotation.defaultValue().isEmpty()) {
            return annotation.defaultValue();
        }
        throw new ObjectBuildException(
            "Brak wartości konfiguracji dla klucza " + annotation.value());
    }

    private Object convert(String value, Class<?> targetType) {
        if (targetType == String.class) {
            return value;
        }
        if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value);
        }
        if (targetType == long.class || targetType == Long.class) {
            return Long.parseLong(value);
        }
        if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        }
        throw new ObjectBuildException("Nieobsługiwany typ: " + targetType.getName());
    }
}
