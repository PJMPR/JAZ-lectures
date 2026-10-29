package pl.pjatk.jaz.annotations.objectbuilder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Oznacza metodę wywoływaną po utworzeniu obiektu i uzupełnieniu jego pól.
 *
 * <p>Builder przyjmuje kontrakt, że metoda z tą adnotacją nie ma parametrów
 * i zwraca {@code void}. {@code @Target} potrafi ograniczyć miejsce użycia,
 * ale nie potrafi wymusić sygnatury. Sprawdza ją więc kod refleksyjny.</p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PostBuild {
}
