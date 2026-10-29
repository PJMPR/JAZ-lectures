package pl.pjatk.jaz.annotations.objectbuilder.model;

import pl.pjatk.jaz.annotations.objectbuilder.annotation.BuildConstructor;
import pl.pjatk.jaz.annotations.objectbuilder.annotation.Buildable;
import pl.pjatk.jaz.annotations.objectbuilder.annotation.ConfigValue;
import pl.pjatk.jaz.annotations.objectbuilder.annotation.PostBuild;
import pl.pjatk.jaz.annotations.objectbuilder.core.ObjectBuilder;

/**
 * Model demonstrujący wszystkie miejsca użycia adnotacji obsługiwane przez
 * przykładowy builder.
 */
@Buildable // TYPE: cała klasa może zostać przekazana do ObjectBuilder.build().
public class ServerConfiguration {
    private final String applicationName;
    private final int port;

    // FIELD: wartość zostanie ustawiona po wykonaniu konstruktora.
    // Jeżeli brak klucza server.timeout, builder użyje tekstu "5000",
    // a następnie przekonwertuje go na long.
    @ConfigValue(value = "server.timeout", defaultValue = "5000")
    private long timeoutMillis;

    private boolean initialized;

    // CONSTRUCTOR: builder wybiera właśnie ten konstruktor.
    @BuildConstructor
    public ServerConfiguration(
        // PARAMETER: każdy argument wskazuje swój klucz konfiguracji.
        @ConfigValue("app.name") String applicationName,
        @ConfigValue(value = "server.port", defaultValue = "8080") int port
    ) {
        this.applicationName = applicationName;
        this.port = port;
    }

    // METHOD: builder wywoła metodę po wstrzyknięciu oznaczonych pól.
    @PostBuild
    private void validateAndInitialize() {
        if (applicationName.isBlank()) {
            throw new IllegalStateException("Nazwa aplikacji nie może być pusta");
        }
        if (port < 1 || port > 65_535) {
            throw new IllegalStateException("Port poza zakresem: " + port);
        }
        initialized = true;
    }

    @Override
    public String toString() {
        return "ServerConfiguration{" +
            "applicationName='" + applicationName + '\'' +
            ", port=" + port +
            ", timeoutMillis=" + timeoutMillis +
            ", initialized=" + initialized +
            '}';
    }
}
