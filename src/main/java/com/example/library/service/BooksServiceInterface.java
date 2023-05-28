package com.example.library.service;

import com.example.library.entity.Book;

import java.util.List;

public interface BooksServiceInterface {
    List<Book> getAll();

    void save(Book book);

    void delete(long id);
}
