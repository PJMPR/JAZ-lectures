package pl.pjatk.jaz.springintro;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.pjatk.jaz.springintro.config.ScanConfiguration;
import pl.pjatk.jaz.springintro.scanned.ScannedRepository;
import pl.pjatk.jaz.springintro.scanned.ScannedService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ComponentScanTest {
    @Test
    void discoversComponentAndInjectsRepository() {
        try (var context = new AnnotationConfigApplicationContext(ScanConfiguration.class)) {
            assertNotNull(context.getBean(ScannedService.class));
            assertNotNull(context.getBean(ScannedRepository.class));
        }
    }
}
