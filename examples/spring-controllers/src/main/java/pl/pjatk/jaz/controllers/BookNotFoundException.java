package pl.pjatk.jaz.controllers;
class BookNotFoundException extends RuntimeException { BookNotFoundException(long id) { super("Book with id " + id + " was not found"); } }
