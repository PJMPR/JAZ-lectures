package pl.pjatk.jaz.annotations.objectbuilder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Łączy parametr konstruktora albo pole z wartością w konfiguracji buildera.
 *
 * <p>Jedna definicja adnotacji może dopuszczać kilka celów. Tutaj
 * {@link ElementType#PARAMETER} obsługuje argumenty konstruktora, a
 * {@link ElementType#FIELD} umożliwia późniejsze ustawianie pól.</p>
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigValue {

    /**
     * Klucz używany do pobrania tekstowej wartości z konfiguracji.
     *
     * <p>Nazwa {@code value} pozwala stosować skrót
     * {@code @ConfigValue("server.port")} zamiast
     * {@code @ConfigValue(value = "server.port")}.</p>
     */
    String value();

    /**
     * Wartość używana, gdy konfiguracja nie zawiera wskazanego klucza.
     * Pusty tekst oznacza w tym przykładzie brak wartości domyślnej.
     */
    String defaultValue() default "";
}
