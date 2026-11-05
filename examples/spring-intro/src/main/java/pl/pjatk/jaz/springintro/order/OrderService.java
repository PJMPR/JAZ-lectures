package pl.pjatk.jaz.springintro.order;

import pl.pjatk.jaz.springintro.notification.NotificationGateway;

public final class OrderService {
    private final OrderRepository repository;
    private final NotificationGateway notifications;

    // Konstruktor opisuje pełny kontrakt obiektu. Spring dostarczy oba elementy.
    public OrderService(OrderRepository repository, NotificationGateway notifications) {
        this.repository = repository;
        this.notifications = notifications;
    }

    public void placeOrder(String orderNumber) {
        repository.save(orderNumber);
        notifications.sendConfirmation(orderNumber);
    }
}
