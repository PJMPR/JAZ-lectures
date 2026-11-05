package pl.pjatk.jaz.springintro.scanned;

import org.springframework.stereotype.Repository;

@Repository
public class ScannedRepository {
    public void save(String value) {
        System.out.println("Component scan zapisał: " + value);
    }
}
