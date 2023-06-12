package com.example.library.controller.Interface;

import com.example.library.entity.Author;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AuthorController {
    @GetMapping
    ResponseEntity<List<Author>> getAllAuthors();

    @GetMapping("/{id}")
    ResponseEntity<Author> getAuthorById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Author> createAuthor(@RequestBody Author author);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAuthor(@PathVariable Long id);
}
