package com.example.library.service.Interface;

import com.example.library.entity.Book;
import com.example.library.entity.Review;
import com.example.library.repository.Interface.BooksRepository;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    void setBooksRepository(BooksRepository booksRepository);

    Optional<Book> findById(long id);

    List<Book> getAll();

    Book save(Book book);

    void deleteById(long id);
}
