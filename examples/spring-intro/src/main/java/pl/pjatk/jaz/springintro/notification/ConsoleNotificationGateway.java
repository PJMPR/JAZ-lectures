package pl.pjatk.jaz.springintro.notification;

public final class ConsoleNotificationGateway implements NotificationGateway {
    @Override
    public void sendConfirmation(String orderNumber) {
        System.out.println("Wysłano potwierdzenie dla: " + orderNumber);
    }
}
