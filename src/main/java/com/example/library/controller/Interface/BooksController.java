package com.example.library.controller.Interface;

import com.example.library.entity.Book;
import com.example.library.service.Implement.BooksServiceImpl;
import com.example.library.service.Implement.ReviewServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface BooksController {
    void setBooksService(BooksServiceImpl booksService);

    void setReviewService(ReviewServiceImpl reviewService);

    @GetMapping
    ResponseEntity<List<Book>> getAllBooks();

    @GetMapping("/{id}")
    ResponseEntity<Optional<Book>> getBookById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Book> createBook(@RequestBody Book book);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBook(@PathVariable Long id);
}
