package com.example.library.service.Implement;

import com.example.library.entity.Book;
import com.example.library.repository.Interface.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService implements com.example.library.service.Interface.BooksService {
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
        try {
            return booksRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error occurred while finding book by ID: {}", id, e);
            throw e;
        }
    }

    @Override
    public List<Book> getAll() {
        logger.debug("inside getAll() method");
        try {
            return booksRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all books", e);
            throw e;
        }
    }

    @Override
    public Book save(Book book) {
        logger.debug("inside save() method");
        try {
            return booksRepository.save(book);
        } catch (Exception e) {
            logger.error("Error occurred while saving book: {}", book, e);
            throw e;
        }
    }

    @Override
    public void deleteById(long id) {
        logger.debug("inside delete() method");
        try {
            booksRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting book by ID: {}", id, e);
            throw e;
        }
    }
}
