package pl.pjatk.jaz.generics.genericclass;

public class Box<T> {
    private final T value;

    public Box(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
