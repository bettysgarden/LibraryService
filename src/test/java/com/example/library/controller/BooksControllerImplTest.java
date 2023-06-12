package com.example.library.controller;

import com.example.library.controller.Implement.BooksControllerImpl;
import com.example.library.entity.Book;
import com.example.library.service.Implement.BooksServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BooksControllerImplTest {
    private MockMvc mockMvc;
    @InjectMocks
    private BooksControllerImpl booksControllerImpl;

    @Mock
    private BooksServiceImpl booksServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        booksControllerImpl = new BooksControllerImpl(booksServiceImpl);
        mockMvc = MockMvcBuilders.standaloneSetup(booksControllerImpl).build();
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(
                new Book(1L, "Title 1", 2023, "Description 1", "Cover 1"),
                new Book(2L, "Title 2", 2023, "Description 1", "Cover 1")
        );
        when(booksServiceImpl.getAll()).thenReturn(books);

        ResponseEntity<List<Book>> response = booksControllerImpl.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
        verify(booksServiceImpl, times(1)).getAll();
    }

    @Test
    void getBookById_NotFound() throws Exception {
        Long bookId = 1L;

        when(booksServiceImpl.findById(bookId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/{id}", bookId))
                .andExpect(status().isNotFound());

        verify(booksServiceImpl, times(1)).findById(bookId);
        verifyNoMoreInteractions(booksServiceImpl);
    }

    @Test
    void testGetBookById() {
        Long bookId = 1L;
        Book book = new Book(1L, "Title 1", 2023, "Description 1", "Cover 1");
        Optional<Book> optionalBook = Optional.of(book);
        when(booksServiceImpl.findById(bookId)).thenReturn(optionalBook);

        ResponseEntity<Optional<Book>> response = booksControllerImpl.getBookById(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalBook, response.getBody());
        verify(booksServiceImpl, times(1)).findById(bookId);
    }

    @Test
    void testCreateBook() {
        Book book = new Book(1L, "Title 1", 2023, "Description 1", "Cover 1");
        when(booksServiceImpl.save(book)).thenReturn(book);

        ResponseEntity<Book> response = booksControllerImpl.createBook(book);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(book, response.getBody());
        verify(booksServiceImpl, times(1)).save(book);
    }

    @Test
    void testDeleteBook() {
        Long bookId = 1L;

        ResponseEntity<Void> response = booksControllerImpl.deleteBook(bookId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(booksServiceImpl, times(1)).deleteById(bookId);
    }
}

