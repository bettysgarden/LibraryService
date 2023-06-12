package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.Implement.BooksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BooksControllerTest {

    private BooksController booksController;

    @Mock
    private BooksService booksService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        booksController = new BooksController(booksService);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(
                new Book(1L, "Title 1", 2023, "Description 1", "Cover 1"),
                new Book(2L, "Title 2", 2023, "Description 1", "Cover 1")
        );
        when(booksService.getAll()).thenReturn(books);

        ResponseEntity<List<Book>> response = booksController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
        verify(booksService, times(1)).getAll();
    }

    @Test
    void testGetBookById() {
        Long bookId = 1L;
        Book book = new Book(1L, "Title 1", 2023, "Description 1", "Cover 1");
        Optional<Book> optionalBook = Optional.of(book);
        when(booksService.findById(bookId)).thenReturn(optionalBook);

        ResponseEntity<Optional<Book>> response = booksController.getBookById(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalBook, response.getBody());
        verify(booksService, times(1)).findById(bookId);
    }

    @Test
    @Disabled
    void testGetBookById_NotFound() {
        Long bookId = 1L;
        when(booksService.findById(bookId)).thenReturn(Optional.empty());

        ResponseEntity<Optional<Book>> response = booksController.getBookById(bookId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(Optional.empty(), response.getBody());
        verify(booksService, times(1)).findById(bookId);
    }

    @Test
    void testCreateBook() {
        Book book = new Book(1L, "Title 1", 2023, "Description 1", "Cover 1");
        when(booksService.save(book)).thenReturn(book);

        ResponseEntity<Book> response = booksController.createBook(book);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(book, response.getBody());
        verify(booksService, times(1)).save(book);
    }

    @Test
    void testDeleteBook() {
        Long bookId = 1L;

        ResponseEntity<Void> response = booksController.deleteBook(bookId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(booksService, times(1)).deleteById(bookId);
    }
}

