package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BooksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/api")
public class BooksController {
    private static final Logger logger = LoggerFactory.getLogger(BooksService.class);

    @Autowired
    private BooksService booksService;

    @GetMapping()
    public ResponseEntity<List<Book>> getAll() {
        logger.debug("inside BooksController.getAll() method");
        List<Book> booksList = booksService.getAll();
        return ResponseEntity.ok(booksList);
    }
}

