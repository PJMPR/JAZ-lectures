package pl.pjatk.jaz.reflection;

import pl.pjatk.jaz.reflection.model.Product;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public final class ReflectionDemo {
    private ReflectionDemo() {
    }

    public static Product createProduct() throws ReflectiveOperationException {
        Constructor<Product> constructor = Product.class.getConstructor(
                String.class, String.class, BigDecimal.class, int.class);
        return constructor.newInstance("P-101", "Klawiatura", new BigDecimal("249.90"), 8);
    }

    public static Object readField(Object target, String fieldName) throws ReflectiveOperationException {
        Field field = target.getClass().getDeclaredField(fieldName);
        if (!field.trySetAccessible()) {
            throw new IllegalAccessException("Brak dostępu do pola " + fieldName);
        }
        return field.get(target);
    }

    public static Object invoke(Object target, String methodName, Class<?>[] parameterTypes, Object... arguments)
            throws ReflectiveOperationException {
        Method method = target.getClass().getMethod(methodName, parameterTypes);
        try {
            method.setAccessible(true);
            return method.invoke(target, arguments);
        } catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            System.err.println("Wywołana metoda zgłosiła: " + cause);
            throw exception;
        }
    }
}
