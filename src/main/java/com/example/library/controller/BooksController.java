package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/api")
public class BooksController {
    @Autowired
    private BooksService booksService;

    @GetMapping()
    public ResponseEntity<List<Book>> getAll() {
        List<Book> booksList = booksService.getAll();
        return ResponseEntity.ok(booksList);
    }
}

