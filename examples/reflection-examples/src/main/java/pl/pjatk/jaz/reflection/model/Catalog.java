package pl.pjatk.jaz.reflection.model;

import java.util.List;
import java.util.Map;

public record Catalog(String name, List<Product> products, Map<String, Product> byId) {
}
