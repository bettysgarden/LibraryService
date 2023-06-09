package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.Implement.BooksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/books")
//public class BooksController {
//
//    private final BooksService bookService;
//
//    @Autowired
//    public BooksController(BooksService bookService) {
//        this.bookService = bookService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Book>> getAllBooks() {
//        List<Book> books = bookService.getAll();
//        return ResponseEntity.ok(books);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Long id) {
//        Optional<Book> book = bookService.findById(id);
//        if (book.isPresent()) {
//            return ResponseEntity.ok(book);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Book> createBook(@RequestBody Book book) {
//        Book createdBook = bookService.save(book);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
//        bookService.deleteById(id);
//        return ResponseEntity.noContent().build();
//
//    }
//}


@Controller
public class BooksController {
    @Autowired
    private BooksService booksService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("booksList", booksService.getAll());
        return "index";
    }
}

