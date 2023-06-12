package com.example.library.service.Interface;

import com.example.library.entity.Author;
import com.example.library.repository.Interface.AuthorRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    void setAuthorRepository(AuthorRepository authorRepository);

    Optional<Author> findById(long id);

    List<Author> getAll();

    Author save(Author author);

    void deleteById(long id);
}
