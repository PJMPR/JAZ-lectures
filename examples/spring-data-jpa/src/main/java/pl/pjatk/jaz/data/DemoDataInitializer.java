package pl.pjatk.jaz.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Buduje większy, powtarzalny zbiór danych do ćwiczenia zapytań,
 * paginacji, relacji i problemu N+1.
 */
@Component
public class DemoDataInitializer implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DemoDataInitializer.class);

    private static final int AUTHOR_COUNT = 30;
    private static final int PUBLISHER_COUNT = 8;
    private static final int CATEGORY_COUNT = 15;
    private static final int BOOK_COUNT = 500;

    private final AuthorRepository authors;
    private final PublisherRepository publishers;
    private final CategoryRepository categories;
    private final BookRepository books;

    public DemoDataInitializer(AuthorRepository authors,
                               PublisherRepository publishers,
                               CategoryRepository categories,
                               BookRepository books) {
        this.authors = authors;
        this.publishers = publishers;
        this.categories = categories;
        this.books = books;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (books.count() > 0) {
            log.info("Baza zawiera już {} książek — pomijam generowanie danych", books.count());
            return;
        }

        List<Author> authorPool = createAuthors();
        List<Publisher> publisherPool = createPublishers();
        List<Category> categoryPool = createCategories();
        List<Book> bookPool = createBooks(authorPool, publisherPool, categoryPool);

        books.saveAll(bookPool);
        books.flush();

        log.info("Wygenerowano: {} autorów, {} wydawców, {} kategorii i {} książek",
                authorPool.size(), publisherPool.size(), categoryPool.size(), bookPool.size());
    }

    private List<Author> createAuthors() {
        List<Author> result = new ArrayList<>();
        for (int i = 1; i <= AUTHOR_COUNT; i++) {
            result.add(new Author("Autor %02d".formatted(i)));
        }
        return authors.saveAll(result);
    }

    private List<Publisher> createPublishers() {
        List<Publisher> result = new ArrayList<>();
        for (int i = 1; i <= PUBLISHER_COUNT; i++) {
            result.add(new Publisher("Wydawnictwo %02d".formatted(i)));
        }
        return publishers.saveAll(result);
    }

    private List<Category> createCategories() {
        List<Category> result = new ArrayList<>();
        for (int i = 1; i <= CATEGORY_COUNT; i++) {
            result.add(new Category("Kategoria %02d".formatted(i)));
        }
        return categories.saveAll(result);
    }

    private List<Book> createBooks(List<Author> authorPool,
                                   List<Publisher> publisherPool,
                                   List<Category> categoryPool) {
        List<Book> result = new ArrayList<>();

        for (int i = 1; i <= BOOK_COUNT; i++) {
            BigDecimal price = BigDecimal.valueOf(20 + (i * 37L) % 280)
                    .add(BigDecimal.valueOf((i % 10) * 0.09));
            Book book = new Book(
                    "Książka demonstracyjna %03d".formatted(i),
                    i % 5 != 0,
                    price);

            Author author = authorPool.get((i - 1) % authorPool.size());
            Publisher publisher = publisherPool.get((i * 3 - 1) % publisherPool.size());
            author.addBook(book);
            publisher.addBook(book);

            book.setDetails(new BookDetails(
                    "978-83-%04d-%03d-%d".formatted(i % 10_000, i % 1_000, i % 10),
                    100 + (i * 17) % 700));

            int categoriesForBook = 1 + i % 3;
            for (int offset = 0; offset < categoriesForBook; offset++) {
                int categoryIndex = (i * 7 + offset * 5) % categoryPool.size();
                book.addCategory(categoryPool.get(categoryIndex));
            }
            result.add(book);
        }
        return result;
    }
}
