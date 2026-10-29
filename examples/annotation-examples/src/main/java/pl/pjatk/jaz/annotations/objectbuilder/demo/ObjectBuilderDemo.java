package pl.pjatk.jaz.annotations.objectbuilder.demo;

import java.util.Map;

import pl.pjatk.jaz.annotations.objectbuilder.core.ObjectBuilder;
import pl.pjatk.jaz.annotations.objectbuilder.model.ServerConfiguration;

/**
 * Punkt startowy przykładu buildera.
 */
public class ObjectBuilderDemo {
    public static void main(String[] args) {
        Map<String, String> values = Map.of(
            "app.name", "JAZ annotations",
            "server.port", "9090"
            // Brak server.timeout demonstruje defaultValue z @ConfigValue.
        );

        ObjectBuilder builder = new ObjectBuilder(values);

        // Literał ServerConfiguration.class przekazuje builderowi definicję
        // klasy. Wynik zachowuje typ dzięki sygnaturze <T> T build(Class<T>).
        ServerConfiguration configuration =
            builder.build(ServerConfiguration.class);

        System.out.println(configuration);
    }
}
