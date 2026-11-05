package pl.pjatk.jaz.springintro.order;

import java.util.ArrayList;
import java.util.List;

public final class InMemoryOrderRepository implements OrderRepository {
    private final List<String> orders = new ArrayList<>();

    @Override
    public void save(String orderNumber) {
        orders.add(orderNumber);
        System.out.println("Zapisano zamówienie: " + orderNumber);
    }

    public List<String> findAll() {
        return List.copyOf(orders);
    }
}
