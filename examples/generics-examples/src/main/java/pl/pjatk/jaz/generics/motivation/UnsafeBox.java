package pl.pjatk.jaz.generics.motivation;

/** Przykład historyczny: Object wymaga rzutowania i nie zapewnia bezpieczeństwa typów. */
public class UnsafeBox {
    private final Object value;

    public UnsafeBox(Object value) {
        this.value = value;
    }

    public Object get() {
        return value;
    }
}
