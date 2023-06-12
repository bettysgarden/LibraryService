package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.repository.Interface.AuthorRepository;
import com.example.library.service.Implement.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById() {
        Long authorId = 1L;
        Author author = new Author(null, "Author 1");
        Optional<Author> optionalAuthor = Optional.of(author);

        when(authorRepository.findById(authorId)).thenReturn(optionalAuthor);

        Optional<Author> result = authorServiceImpl.findById(authorId);

        assertEquals(optionalAuthor, result);
        verify(authorRepository, times(1)).findById(authorId);
        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    void getAll() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(null,"Author 1"));
        authors.add(new Author(null, "Author 2"));

        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorServiceImpl.getAll();

        assertEquals(authors, result);
        verify(authorRepository, times(1)).findAll();
        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    void save() {
        Author author = new Author(null, "Author 1");

        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Author result = authorServiceImpl.save(author);

        assertEquals(author, result);
        verify(authorRepository, times(1)).save(author);
        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    void deleteById() {
        Long authorId = 1L;

        doNothing().when(authorRepository).deleteById(authorId);

        authorServiceImpl.deleteById(authorId);

        verify(authorRepository, times(1)).deleteById(authorId);
        verifyNoMoreInteractions(authorRepository);
    }
}

