package pl.pjatk.jaz.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Minimalny mechanizm przetwarzający adnotację {@link Scheduled}.
 *
 * <p>Przykład pokazuje dwa osobne mechanizmy:</p>
 * <ol>
 *   <li>adnotacja deklaruje i przechowuje metadane,</li>
 *   <li>refleksja odnajduje metadane i na ich podstawie steruje programem.</li>
 * </ol>
 *
 * <p>Ten runner nie jest pełnym harmonogramem. Wypisuje deklarowane opóźnienie,
 * ale uruchamia aktywną metodę od razu. Celem przykładu jest pokazanie sposobu
 * odczytu adnotacji, a nie implementacji odmierzania czasu.</p>
 */
public class AnnotationRunner {

    public static void main(String[] args) {
        // Runner otrzymuje zwykły obiekt. Jego rzeczywistą klasę pozna
        // w runJobs za pomocą target.getClass().
        runJobs(new CleanupJob());
    }

    /**
     * Wyszukuje metody oznaczone {@link Scheduled} i uruchamia aktywne zadania.
     *
     * @param target obiekt, którego metody mają zostać zbadane i ewentualnie
     *               wywołane
     */
    static void runJobs(Object target) {
        // getClass() zwraca obiekt Class opisujący rzeczywisty typ instancji.
        // getDeclaredMethods() zwraca metody zadeklarowane bezpośrednio w tej
        // klasie, niezależnie od tego, czy posiadają @Scheduled.
        for (Method method : target.getClass().getDeclaredMethods()) {

            // Method jest obiektem refleksji opisującym jedną metodę.
            // getAnnotation(...) szuka na niej adnotacji wskazanego typu.
            // Scheduled.class jest literałem klasy reprezentującym typ
            // adnotacji. Wynikiem jest obiekt Scheduled albo null, gdy metoda
            // nie ma tej adnotacji.
            Scheduled job = method.getAnnotation(Scheduled.class);

            // job == null odrzuca metody bez @Scheduled.
            // job.enabled() odczytuje wartość elementu enabled z konkretnego
            // wystąpienia adnotacji. Może to być wartość jawna albo domyślna.
            if (job == null || !job.enabled()) {
                continue;
            }

            // Adnotacja wskazuje metodę, ale sama nie gwarantuje, że metoda ma
            // sygnaturę obsługiwaną przez runner. Dlatego kontrakt sprawdzamy
            // przed wywołaniem refleksyjnym.
            validate(method);

            // Elementy adnotacji odczytuje się jak bezargumentowe metody.
            // Tutaj pobieramy name oraz delaySeconds z metadanych zapisanych
            // przy aktualnie analizowanej metodzie.
            System.out.printf("Uruchamiam %s po %d s%n", job.name(), job.delaySeconds());
            try {
                // invoke(target) wykonuje metodę na przekazanej instancji.
                // Nie przekazujemy kolejnych argumentów, ponieważ kontrakt
                // @Scheduled w tym przykładzie wymaga metody bez parametrów.
                method.invoke(target);
            } catch (IllegalAccessException | InvocationTargetException exception) {
                // IllegalAccessException oznacza brak dostępu do metody.
                // InvocationTargetException opakowuje wyjątek rzucony przez
                // samą wywołaną metodę. Framework zwykle zamienia te techniczne
                // wyjątki na błąd opisujący własny kontrakt.
                throw new IllegalStateException("Nie można uruchomić zadania: " + method, exception);
            }
        }
    }

    /**
     * Sprawdza konwencję przyjętą przez przykładowy runner.
     *
     * <p>{@link java.lang.annotation.Target} może ograniczyć użycie adnotacji
     * do metod, ale nie potrafi wymusić liczby parametrów ani typu zwracanego.
     * Takie reguły musi sprawdzić procesor adnotacji lub kod refleksyjny.</p>
     */
    private static void validate(Method method) {
        // getParameterCount() odczytuje liczbę parametrów z definicji metody.
        // getReturnType() zwraca obiekt Class reprezentujący typ wyniku.
        // void.class jest literałem klasy dla typu void.
        if (method.getParameterCount() != 0 || method.getReturnType() != void.class) {
            throw new IllegalStateException("@Scheduled wymaga metody void bez parametrów: " + method);
        }
    }
}
