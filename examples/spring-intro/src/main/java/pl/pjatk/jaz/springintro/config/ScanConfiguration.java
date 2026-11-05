package pl.pjatk.jaz.springintro.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("pl.pjatk.jaz.springintro.scanned")
public class ScanConfiguration {
    // Definicje beanów znajdują się w klasach oznaczonych @Component
    // oraz jego wyspecjalizowanymi wariantami, na przykład @Repository.
}
