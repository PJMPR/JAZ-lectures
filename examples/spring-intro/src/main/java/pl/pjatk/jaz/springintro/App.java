package pl.pjatk.jaz.springintro;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.pjatk.jaz.springintro.config.ExplicitConfiguration;
import pl.pjatk.jaz.springintro.order.OrderService;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        // Context jest kontenerem IoC. Rejestruje definicje beanów, tworzy obiekty
        // i przekazuje zależności do konstruktorów.
        try (var context = new AnnotationConfigApplicationContext(ExplicitConfiguration.class)) {
            // getBean służy tu wyłącznie jako punkt wejścia demonstracji.
            // Kod domenowy nie powinien sam wyszukiwać zależności w kontenerze.
            var orderService = context.getBean(OrderService.class);
            orderService.placeOrder("SPRING-101");
        }
    }
}
