package pl.pjatk.jaz.generics.bounds;

public final class Numbers {
    private Numbers() {
    }

    public static <T extends Number> double asDouble(T number) {
        return number.doubleValue();
    }
}
