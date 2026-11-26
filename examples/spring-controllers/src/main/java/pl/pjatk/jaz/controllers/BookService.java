package pl.pjatk.jaz.controllers;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
@Service
class BookService {
    private final Map<Long, BookResponse> books = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong();
    BookService() { create(new CreateBookRequest("Solaris", "Stanisław Lem", 340)); create(new CreateBookRequest("A Wizard of Earthsea", "Ursula K. Le Guin", 205)); }
    synchronized List<BookResponse> findAll(String author, int limit) { int safeLimit = Math.max(1, Math.min(limit, 100)); return books.values().stream().filter(book -> author == null || book.author().toLowerCase().contains(author.toLowerCase())).limit(safeLimit).toList(); }
    synchronized BookResponse findOne(long id) { BookResponse book = books.get(id); if (book == null) throw new BookNotFoundException(id); return book; }
    synchronized BookResponse create(CreateBookRequest request) { long id = sequence.incrementAndGet(); BookResponse book = new BookResponse(id, request.title(), request.author(), request.pages()); books.put(id, book); return book; }
    synchronized BookResponse replace(long id, CreateBookRequest request) { findOne(id); BookResponse book = new BookResponse(id, request.title(), request.author(), request.pages()); books.put(id, book); return book; }
    synchronized void delete(long id) { findOne(id); books.remove(id); }
}
