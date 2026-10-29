package pl.pjatk.jaz.annotations.objectbuilder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.pjatk.jaz.annotations.objectbuilder.core.ObjectBuilder;

/**
 * Oznacza klasę, której instancje może tworzyć {@link ObjectBuilder}.
 *
 * <p>{@link ElementType#TYPE} pozwala użyć adnotacji na klasie. Retencja
 * {@link RetentionPolicy#RUNTIME} jest konieczna, ponieważ builder sprawdza
 * obecność adnotacji podczas działania programu.</p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Buildable {
}
