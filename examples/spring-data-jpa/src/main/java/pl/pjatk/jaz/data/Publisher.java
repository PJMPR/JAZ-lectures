package pl.pjatk.jaz.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String name;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.PERSIST)
    private final List<Book> books = new ArrayList<>();

    protected Publisher() {}

    public Publisher(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
        book.assignPublisher(this);
    }
}
