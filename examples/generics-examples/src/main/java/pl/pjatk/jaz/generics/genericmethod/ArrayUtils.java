package pl.pjatk.jaz.generics.genericmethod;

public final class ArrayUtils {
    private ArrayUtils() {
    }

    public static <T> T first(T[] items) {
        if (items.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        return items[0];
    }
}
