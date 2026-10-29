package pl.pjatk.jaz.annotations.objectbuilder.core;

/**
 * Wyjątek domenowy ukrywający techniczne wyjątki API refleksji.
 */
public class ObjectBuildException extends RuntimeException {
    public ObjectBuildException(String message) {
        super(message);
    }

    public ObjectBuildException(String message, Throwable cause) {
        super(message, cause);
    }
}
