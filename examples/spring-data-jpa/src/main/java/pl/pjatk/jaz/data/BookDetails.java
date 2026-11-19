package pl.pjatk.jaz.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_details")
public class BookDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 32)
    private String isbn;

    @Column(nullable = false)
    private int pageCount;

    @OneToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false, unique = true)
    private Book book;

    protected BookDetails() {}

    public BookDetails(String isbn, int pageCount) {
        this.isbn = isbn;
        this.pageCount = pageCount;
    }

    void assignTo(Book book) {
        this.book = book;
    }
}
