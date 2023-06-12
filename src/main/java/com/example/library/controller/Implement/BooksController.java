package com.example.library.controller.Implement;

import com.example.library.entity.Book;
import com.example.library.service.Implement.BooksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController implements com.example.library.controller.Interface.BooksController {

    private final BooksService bookService;

    private final Logger logger = LoggerFactory.getLogger(BooksController.class);

    @Autowired
    public BooksController(BooksService bookService) {
        this.bookService = bookService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        logger.info("Getting all books");
        try {
            List<Book> books = bookService.getAll();
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            logger.error("Error occurred while getting all books", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Long id) {
        logger.info("Getting book by ID: {}", id);
        try {
            Optional<Book> book = bookService.findById(id);
            if (book.isPresent()) {
                return ResponseEntity.ok(book);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error occurred while getting book by ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        logger.info("Creating a new book: {}", book);
        try {
            Book createdBook = bookService.save(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } catch (Exception e) {
            logger.error("Error occurred while creating a book: {}", book, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        logger.info("Deleting book with ID: {}", id);
        try {
            bookService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error occurred while deleting book with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
