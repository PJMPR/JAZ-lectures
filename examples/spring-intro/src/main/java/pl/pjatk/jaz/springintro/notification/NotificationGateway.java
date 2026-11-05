package pl.pjatk.jaz.springintro.notification;

public interface NotificationGateway {
    void sendConfirmation(String orderNumber);
}
