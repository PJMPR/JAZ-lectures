package pl.pjatk.jaz.springintro;

import org.junit.jupiter.api.Test;
import pl.pjatk.jaz.springintro.notification.NotificationGateway;
import pl.pjatk.jaz.springintro.order.OrderRepository;
import pl.pjatk.jaz.springintro.order.OrderService;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {
    @Test
    void delegatesWorkToInjectedDependencies() {
        var saved = new StringBuilder();
        var notified = new StringBuilder();
        OrderRepository repository = saved::append;
        NotificationGateway gateway = notified::append;

        var service = new OrderService(repository, gateway);
        service.placeOrder("T-42");

        assertEquals("T-42", saved.toString());
        assertEquals("T-42", notified.toString());
    }
}
