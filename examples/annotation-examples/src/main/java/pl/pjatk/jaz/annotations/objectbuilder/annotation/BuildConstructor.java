package pl.pjatk.jaz.annotations.objectbuilder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.pjatk.jaz.annotations.objectbuilder.core.ObjectBuilder;

/**
 * Wskazuje konstruktor, którego {@link ObjectBuilder} ma użyć do utworzenia
 * obiektu.
 *
 * <p>Adnotacja nie potrzebuje elementów. Sama jej obecność stanowi znacznik.
 * Cel {@link ElementType#CONSTRUCTOR} uniemożliwia użycie jej na metodzie,
 * polu lub klasie.</p>
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
public @interface BuildConstructor {
}
