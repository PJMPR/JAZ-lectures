package pl.pjatk.jaz.lambdas.livecoding;

import java.util.List;

public class NotificationPipeline {
    public static void main(String[] args) {
        List<String> messages = List.of(" INFO: start ", "ERROR: timeout", "DEBUG: cache");
        // TODO: usuń spacje, wybierz komunikaty ERROR i wypisz je małymi literami.
        System.out.println(messages);
    }
}
