package pl.pjatk.jaz.springintro.scanned;

import org.springframework.stereotype.Component;

// @Component rejestruje klasę jako kandydata wykrywanego przez component scan.
// @Service jest jego wyspecjalizowanym wariantem przeznaczonym dla usług.
@Component
public class ScannedService {
    private final ScannedRepository repository;

    public ScannedService(ScannedRepository repository) {
        this.repository = repository;
    }

    public void run() {
        repository.save("bean wykryty przez adnotację");
    }
}
