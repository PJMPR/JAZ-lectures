package pl.pjatk.jaz.data;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.math.BigDecimal;

@Service
public class LibraryService {
    private final AuthorRepository authors;
    private final BookRepository books;
    private final PublisherRepository publishers;
    private final CategoryRepository categories;

    public LibraryService(AuthorRepository authors, BookRepository books,
                          PublisherRepository publishers, CategoryRepository categories) {
        this.authors = authors;
        this.books = books;
        this.publishers = publishers;
        this.categories = categories;
    }

    @Transactional
    public Book addBook(String title, String authorName, String publisherName,
                        boolean published, BigDecimal price, String... categoryNames) {
        Author author = authors.findByName(authorName)
                .orElseGet(() -> authors.save(new Author(authorName)));
        Publisher publisher = publishers.findByNameIgnoreCase(publisherName)
                .orElseGet(() -> publishers.save(new Publisher(publisherName)));
        Book book = new Book(title, published, price);
        author.addBook(book);
        publisher.addBook(book);
        book.setDetails(new BookDetails("ISBN-" + Math.abs(title.hashCode()), 400));
        for (String categoryName : categoryNames) {
            Category category = categories.findByNameIgnoreCase(categoryName)
                    .orElseGet(() -> categories.save(new Category(categoryName)));
            book.addCategory(category);
        }
        return books.save(book);
    }

    @Transactional(readOnly = true)
    public List<Book> search(String text) {
        return books.search(text);
    }
}
