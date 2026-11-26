package pl.pjatk.jaz.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
class ApiExceptionHandler { @ExceptionHandler(BookNotFoundException.class) ResponseEntity<ProblemDetail> notFound(BookNotFoundException exception) { ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage()); problem.setTitle("Book not found"); return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem); } }
