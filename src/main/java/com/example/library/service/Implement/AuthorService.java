package com.example.library.service.Implement;

import com.example.library.entity.Author;
import com.example.library.repository.Interface.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements com.example.library.service.Interface.AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Optional<Author> findById(long id) {
        logger.debug("inside findById() method");
        try {
            return authorRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error occurred while finding book by ID: {}", id, e);
            throw e;
        }
    }

    @Override
    public List<Author> getAll() {
        logger.debug("inside getAll() method");
        try {
            return authorRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all books", e);
            throw e;
        }
    }

    public Author save(Author author) {
        logger.debug("inside save() method");
        try {
            return authorRepository.save(author);
        } catch (Exception e) {
            logger.error("Error occurred while saving author: {}", author, e);
            throw e;
        }
    }


    @Override
    public void deleteById(long id) {
        logger.debug("inside delete() method");
        try {
            authorRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("An error occurred while deleting author with ID: {}", id, e);
            throw new RuntimeException("Failed to delete author");
        }
    }

}
