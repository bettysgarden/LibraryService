package com.example.library.service.Implement;

import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;
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
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> getAll() {
        logger.debug("inside getAll() method");
        return authorRepository.findAll();
    }

    @Override
    public void save(Author author) {
        logger.debug("inside save() method");
        authorRepository.save(author);
    }

    @Override
    public void delete(long id) {
        logger.debug("inside delete() method");
        authorRepository.deleteById(id);
    }
}
