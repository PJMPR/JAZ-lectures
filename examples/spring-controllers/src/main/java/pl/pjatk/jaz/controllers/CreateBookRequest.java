package pl.pjatk.jaz.controllers;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
public record CreateBookRequest(@NotBlank String title, @NotBlank String author, @Positive int pages) {}
