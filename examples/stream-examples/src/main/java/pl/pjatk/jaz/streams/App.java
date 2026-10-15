package pl.pjatk.jaz.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    record Order(String id, String city, int amount, boolean paid) { }

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("A-101", "Warszawa", 120, true),
                new Order("A-102", "Gdańsk", 80, false),
                new Order("A-103", "Warszawa", 200, true),
                new Order("A-104", "Gdańsk", 150, true)
        );

        System.out.println("Pętla: " + totalsWithLoop(orders));
        System.out.println("Stream: " + totalsWithStream(orders));
    }

    static Map<String, Integer> totalsWithLoop(List<Order> orders) {
        Map<String, Integer> totals = new java.util.HashMap<>();
        for (Order order : orders) {
            if (order.paid()) {
                totals.merge(order.city(), order.amount(), Integer::sum);
            }
        }
        return totals;
    }

    static Map<String, Integer> totalsWithStream(List<Order> orders) {
        return orders.stream()
                .filter(Order::paid)
                .collect(Collectors.groupingBy(
                        Order::city,
                        Collectors.summingInt(Order::amount)
                ));
    }
}
