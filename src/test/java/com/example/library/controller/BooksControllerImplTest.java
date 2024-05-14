package com.example.library.controller;

import com.example.library.controller.Implement.BooksControllerImpl;
import com.example.library.entity.Book;
import com.example.library.service.Implement.BooksServiceImpl;
import com.example.library.service.Implement.ReviewServiceImpl;
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
    private BooksControllerImpl booksController;

    @Mock
    private BooksServiceImpl booksService;

    @Mock
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        booksController.setBooksService(booksService);
        booksController.setReviewService(reviewService);
        mockMvc = MockMvcBuilders.standaloneSetup(booksController).build();
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(
                new Book(1L, "Title 1", "Description 1", "Cover 1"),
                new Book(2L, "Title 2", "Description 1", "Cover 1")
        );
        when(booksService.getAll()).thenReturn(books);

        ResponseEntity<List<Book>> response = booksController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
        verify(booksService, times(1)).getAll();
    }

    @Test
    void getBookById_NotFound() throws Exception {
        Long bookId = 1L;

        when(booksService.findById(bookId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/{id}", bookId))
                .andExpect(status().isNotFound());

        verify(booksService, times(1)).findById(bookId);
        verifyNoMoreInteractions(booksService);
    }

    @Test
    void testGetBookById() {
        Long bookId = 1L;
        Book book = new Book(1L, "Title 1", "Description 1", "Cover 1");
        Optional<Book> optionalBook = Optional.of(book);
        when(booksService.findById(bookId)).thenReturn(optionalBook);

        ResponseEntity<Optional<Book>> response = booksController.getBookById(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalBook, response.getBody());
        verify(booksService, times(1)).findById(bookId);
    }

    @Test
    void testCreateBook() {
        Book book = new Book(1L, "Title 1", "Description 1", "Cover 1");
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

