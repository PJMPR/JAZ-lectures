package pl.pjatk.jaz.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.RecordComponent;
import java.lang.reflect.Type;
import java.util.Arrays;

public final class ClassInspector {
    private ClassInspector() {
    }

    public static void describe(Class<?> type) {
        System.out.printf("%n=== %s ===%n", type.getName());
        System.out.println("Rodzaj: " + kindOf(type));
        System.out.println("Modyfikatory: " + Modifier.toString(type.getModifiers()));
        System.out.println("Nadklasa: " + nameOf(type.getSuperclass()));
        System.out.println("Interfejsy: " + Arrays.toString(type.getInterfaces()));

        // printRecordComponents(type);
        // printFields(type);
        // printConstructors(type);
        // printMethods(type);
    }

    private static String kindOf(Class<?> type) {
        if (type.isRecord()) return "record";
        if (type.isEnum()) return "enum";
        if (type.isInterface()) return "interfejs";
        if (type.isArray()) return "tablica typu " + type.getComponentType().getTypeName();
        if (type.isPrimitive()) return "typ prymitywny";
        return "klasa";
    }

    private static String nameOf(Class<?> type) {
        return type == null ? "brak" : type.getName();
    }

    private static void printRecordComponents(Class<?> type) {
        if (!type.isRecord()) return;
        System.out.println("Składniki rekordu:");
        for (RecordComponent component : type.getRecordComponents()) {
            System.out.printf("  %s %s, akcesor: %s()%n",
                    component.getGenericType().getTypeName(),
                    component.getName(),
                    component.getAccessor().getName());
        }
    }

    private static void printFields(Class<?> type) {
        System.out.println("Zadeklarowane pola:");
        for (Field field : type.getDeclaredFields()) {
            System.out.printf("  %s %s %s%n",
                    Modifier.toString(field.getModifiers()),
                    format(field.getGenericType()),
                    field.getName());
        }
    }

    private static void printConstructors(Class<?> type) {
        System.out.println("Konstruktory:");
        for (Constructor<?> constructor : type.getDeclaredConstructors()) {
            System.out.printf("  %s(%s)%n", type.getSimpleName(), parametersOf(constructor.getParameters()));
        }
    }

    private static void printMethods(Class<?> type) {
        System.out.println("Zadeklarowane metody:");
        for (Method method : type.getDeclaredMethods()) {
            System.out.printf("  %s %s(%s)%n",
                    format(method.getGenericReturnType()),
                    method.getName(),
                    parametersOf(method.getParameters()));
        }
    }

    private static String parametersOf(Parameter[] parameters) {
        return Arrays.stream(parameters)
                .map(parameter -> format(parameter.getParameterizedType()) + " " + parameter.getName())
                .reduce((left, right) -> left + ", " + right)
                .orElse("");
    }

    private static String format(Type type) {
        if (type instanceof Class<?> classType) {
            return classType.getSimpleName();
        }
        if (type instanceof ParameterizedType parameterized) {
            return parameterized.getTypeName().replace("java.lang.", "").replace("java.util.", "");
        }
        return type.getTypeName();
    }
}
