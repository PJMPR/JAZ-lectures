package pl.pjatk.jaz.controllers;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api/books")
class BookController {
    private final BookService service;
    BookController(BookService service) { this.service = service; }
    @GetMapping List<BookResponse> findAll(@RequestParam(required = false) String author, @RequestParam(defaultValue = "20") int limit) { return service.findAll(author, limit); }
    @GetMapping("/{id}") BookResponse findOne(@PathVariable long id) { return service.findOne(id); }
    @PostMapping ResponseEntity<BookResponse> create(@Valid @RequestBody CreateBookRequest request) { BookResponse created = service.create(request); URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.id()).toUri(); return ResponseEntity.created(location).body(created); }
    @PutMapping("/{id}") BookResponse replace(@PathVariable long id, @Valid @RequestBody CreateBookRequest request) { return service.replace(id, request); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) void delete(@PathVariable long id) { service.delete(id); }
}
