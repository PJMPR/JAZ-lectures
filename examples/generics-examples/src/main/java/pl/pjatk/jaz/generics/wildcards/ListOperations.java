package pl.pjatk.jaz.generics.wildcards;

import java.util.List;

public final class ListOperations {
    private ListOperations() {
    }

    public static double sum(List<? extends Number> numbers) {
        double result = 0;
        for (Number number : numbers) {
            result += number.doubleValue();
        }
        return result;
    }

    public static void addDefaults(List<? super Integer> destination) {
        destination.add(10);
        destination.add(20);
    }

    public static <T> void copy(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }
}
