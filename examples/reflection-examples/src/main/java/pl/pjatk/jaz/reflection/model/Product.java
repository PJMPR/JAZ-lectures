package pl.pjatk.jaz.reflection.model;

import java.math.BigDecimal;

public class Product extends CatalogItem implements Priced {
    private final String name;
    private final BigDecimal price;
    private int stock;

    public Product(String id, String name, BigDecimal price, int stock) {
        super(id);
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String name() {
        return name;
    }

    public BigDecimal price() {
        return price;
    }

    public boolean reserve(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (quantity > stock) {
            return false;
        }
        stock -= quantity;
        return true;
    }

    public int stock() {
        return stock;
    }

    private String internalCode() {
        return id() + "-" + name.toUpperCase();
    }
}
