package pl.pjatk.jaz.lambdas.livecoding;

import java.util.List;

public class NotificationPipelineSolution {
    public static void main(String[] args) {
        List<String> messages = List.of(" INFO: start ", "ERROR: timeout", "DEBUG: cache");
        messages.stream().map(String::trim).filter(message -> message.startsWith("ERROR"))
                .map(String::toLowerCase).forEach(System.out::println);
    }
}
