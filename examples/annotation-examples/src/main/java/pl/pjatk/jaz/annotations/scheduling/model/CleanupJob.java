package pl.pjatk.jaz.annotations.scheduling.model;

import pl.pjatk.jaz.annotations.scheduling.annotation.Scheduled;

/**
 * Przykładowy obiekt zawierający metody analizowane przez refleksję.
 *
 * <p>Klasa nie implementuje specjalnego interfejsu i nie zna runnera.
 * Powiązanie powstaje przez umieszczenie metadanych {@link Scheduled}
 * na wybranych metodach.</p>
 */
public class CleanupJob {

    // delaySeconds jest elementem obowiązkowym, ponieważ jego definicja
    // w Scheduled nie zawiera wartości default.
    // name nadpisuje wartość domyślną "job".
    // enabled pomijamy, więc przyjmie domyślną wartość true.
    @Scheduled(delaySeconds = 30, name = "cleanup")
    public void removeExpiredFiles() {
        System.out.println("Usuwam wygasłe pliki");
    }

    // To użycie przekazuje wartości wszystkim trzem elementom adnotacji.
    // Runner odnajdzie adnotację, ale nie uruchomi metody, ponieważ enabled
    // ma wartość false.
    @Scheduled(delaySeconds = 60, name = "disabled-job", enabled = false)
    public void disabledJob() {
        System.out.println("Ta metoda nie powinna się wykonać");
    }

    // Brak @Scheduled oznacza brak metadanych rozpoznawanych przez runner.
    // Metoda pojawi się w tablicy zwróconej przez getDeclaredMethods(),
    // ale getAnnotation(Scheduled.class) zwróci dla niej null.
    public void ordinaryMethod() {
        System.out.println("Zwykła metoda");
    }
}
