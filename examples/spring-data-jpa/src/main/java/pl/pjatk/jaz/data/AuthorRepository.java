package pl.pjatk.jaz.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);

    List<Author> findByNameStartingWithIgnoreCase(String prefix);

    @EntityGraph(attributePaths = "books")
    Optional<Author> findWithBooksById(Long id);
}
