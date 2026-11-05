package pl.pjatk.jaz.springintro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pjatk.jaz.springintro.notification.ConsoleNotificationGateway;
import pl.pjatk.jaz.springintro.notification.NotificationGateway;
import pl.pjatk.jaz.springintro.order.InMemoryOrderRepository;
import pl.pjatk.jaz.springintro.order.OrderRepository;
import pl.pjatk.jaz.springintro.order.OrderService;

@Configuration
public class ExplicitConfiguration {
    @Bean
    OrderRepository orderRepository() {
        return new InMemoryOrderRepository();
    }

    @Bean
    NotificationGateway notificationGateway() {
        return new ConsoleNotificationGateway();
    }

    @Bean
    OrderService orderService(OrderRepository repository, NotificationGateway notifications) {
        // Parametry metody @Bean również są punktami wstrzykiwania zależności.
        return new OrderService(repository, notifications);
    }
}
