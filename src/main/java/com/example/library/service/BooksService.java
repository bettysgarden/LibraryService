package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService implements BooksServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(BooksService.class);

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public void setBooksRepository(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public Optional<Book> findById(long id) {
        logger.debug("inside findById() method");
        return booksRepository.findById(id);
    }

    @Override
    public List<Book> getAll() {
        logger.debug("inside getAll() method");
        return booksRepository.findAll();
    }

    @Override
    public void save(Book book) {
        logger.debug("inside save() method");
        booksRepository.save(book);
    }

    @Override
    public void delete(long id) {
        logger.debug("inside delete() method");
        booksRepository.deleteById(id);
    }
}
