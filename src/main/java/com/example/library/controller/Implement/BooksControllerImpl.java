package com.example.library.controller.Implement;

import com.example.library.controller.Interface.BooksController;
import com.example.library.entity.Book;
import com.example.library.entity.Review;
import com.example.library.service.Implement.BooksServiceImpl;
import com.example.library.service.Implement.ReviewServiceImpl;
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
public class BooksControllerImpl implements BooksController {

    @Autowired
    private  BooksServiceImpl bookService;

    @Autowired
    private  ReviewServiceImpl reviewService;

    private final Logger logger = LoggerFactory.getLogger(BooksControllerImpl.class);

    @Override
    public void setBooksService(BooksServiceImpl booksService) {
        this.bookService = booksService;
    }

    @Override
    public void setReviewService(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
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
    @GetMapping("/reviews/{bookId}")
    public ResponseEntity<List<Review>> getReviewsForBook(@PathVariable Long bookId) {
        logger.info("Getting reviews for book with ID: {}", bookId);
        try {
            Book book = new Book();
            book.setId(bookId);
            List<Review> reviews = reviewService.getReviewsForBook(book);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            logger.error("Error occurred while getting reviews for book with ID: {}", bookId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
