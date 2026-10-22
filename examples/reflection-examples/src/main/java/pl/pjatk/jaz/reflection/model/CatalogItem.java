package pl.pjatk.jaz.reflection.model;

public abstract class CatalogItem {
    private final String id;

    protected CatalogItem(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
