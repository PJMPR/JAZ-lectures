package pl.pjatk.jaz.data;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCaseOrderByTitle(String fragment);

    boolean existsByTitleIgnoreCase(String title);

    long countByAuthorNameIgnoreCase(String authorName);

    List<Book> findTop5ByPublishedTrueOrderByPriceDesc();

    Page<Book> findByPriceBetween(BigDecimal minimum, BigDecimal maximum, Pageable pageable);

    List<Book> findDistinctByCategoriesNameIgnoreCase(String categoryName);

    List<Book> findByPublisherNameAndPublishedTrue(String publisherName);

    @EntityGraph(attributePaths = "author")
    List<Book> findPublished();

    @Query("""
            select b from Book b
            join fetch b.author
            where lower(b.title) like lower(concat('%', :text, '%'))
            order by b.title
            """)
    List<Book> search(@Param("text") String text);

    @Query("""
            select b from Book b
            where b.price >= :minimum
              and b.published = true
            """)
    Page<Book> findPublishedWithMinimumPrice(
            @Param("minimum") BigDecimal minimum, Pageable pageable);

    @Query(value = """
            select b.* from books b
            join book_categories bc on bc.book_id = b.id
            join categories c on c.id = bc.category_id
            where lower(c.name) = lower(:category)
            """, nativeQuery = true)
    List<Book> findByCategoryNative(@Param("category") String category);

    @Modifying(clearAutomatically = true)
    @Query("update Book b set b.published = true where b.id = :id")
    int publishById(@Param("id") Long id);
}
