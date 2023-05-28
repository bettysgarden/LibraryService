package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BooksController {
    @Autowired
    private BooksService booksService;

    @GetMapping("/")
    public String getAll(Model model) {
        List<Book> booksList = booksService.getAll();
        model.addAttribute("booksList", booksList);
        model.addAttribute("listSize", booksList.size());
        return "index";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        booksService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Book book) {
        booksService.save(book);
        return "redirect:/";
    }
}
