package pl.pjatk.jaz.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Własna adnotacja opisująca metodę, którą prosty mechanizm planowania
 * może odnaleźć i uruchomić.
 *
 * <p>Adnotacja przechowuje metadane. Sama nie wywołuje oznaczonej metody
 * ani nie odmierza czasu. Znaczenie tych danych nadaje kod przetwarzający,
 * którym w tym przykładzie jest {@link AnnotationRunner}.</p>
 *
 * <p>Słowo {@code @interface} deklaruje nowy typ adnotacji. Typu tego używamy
 * później jako {@code @Scheduled(...)} oraz przekazujemy go do metod refleksji,
 * na przykład {@code method.getAnnotation(Scheduled.class)}.</p>
 */
@Documented
// @Documented powoduje umieszczenie @Scheduled w dokumentacji Javadoc
// elementu, który został nią oznaczony.
@Retention(RetentionPolicy.RUNTIME)
// @Retention określa czas przechowywania adnotacji.
// RUNTIME oznacza, że informacja pozostaje w pliku .class i JVM udostępnia ją
// podczas działania programu. Bez tej retencji refleksja nie odczytałaby
// @Scheduled za pomocą getAnnotation(...).
@Target(ElementType.METHOD)
// @Target ogranicza miejsca, w których można użyć adnotacji.
// ElementType.METHOD pozwala umieszczać @Scheduled wyłącznie przy metodach.
public @interface Scheduled {

    /**
     * Liczba sekund opóźnienia zadania.
     *
     * <p>Element adnotacji przypomina metodę bez parametrów. Brak słowa
     * {@code default} oznacza, że wartość jest obowiązkowa przy każdym użyciu
     * {@code @Scheduled}.</p>
     */
    long delaySeconds();

    /**
     * Czytelna nazwa zadania.
     *
     * <p>Wartość domyślna pozwala pominąć element {@code name} podczas użycia
     * adnotacji. Odczyt odbywa się przez wywołanie {@code annotation.name()}.</p>
     */
    String name() default "job";

    /**
     * Informacja, czy runner powinien uruchomić zadanie.
     * Domyślna wartość {@code true} oznacza zadanie aktywne.
     */
    boolean enabled() default true;
}
