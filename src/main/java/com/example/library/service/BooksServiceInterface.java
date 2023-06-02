package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BooksRepository;

import java.util.List;
import java.util.Optional;

public interface BooksServiceInterface {
    void setBooksRepository(BooksRepository booksRepository);

    Optional<Book> findById(long id);

    List<Book> getAll();

    void save(Book book);

    void delete(long id);
}
