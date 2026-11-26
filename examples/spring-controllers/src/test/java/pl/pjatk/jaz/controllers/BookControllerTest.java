package pl.pjatk.jaz.controllers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(BookController.class)
@Import({BookService.class, ApiExceptionHandler.class})
class BookControllerTest {
    @Autowired MockMvc mockMvc;
    @Test void returnsBookAsJson() throws Exception { mockMvc.perform(get("/api/books/{id}", 1)).andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON)).andExpect(jsonPath("$.title").value("Solaris")); }
    @Test void createsBookAndReturnsLocation() throws Exception { mockMvc.perform(post("/api/books").contentType(APPLICATION_JSON).content("{\"title\":\"Dune\",\"author\":\"Frank Herbert\",\"pages\":412}")).andExpect(status().isCreated()).andExpect(header().string("Location", "http://localhost/api/books/3")).andExpect(jsonPath("$.id").value(3)); }
    @Test void returnsProblemDetailForMissingBook() throws Exception { mockMvc.perform(get("/api/books/{id}", 999)).andExpect(status().isNotFound()).andExpect(jsonPath("$.title").value("Book not found")); }
}
