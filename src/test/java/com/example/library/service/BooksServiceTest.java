package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BooksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BooksServiceTest {
    @Mock
    private BooksRepository booksRepository;
    private BooksService booksService;

    @BeforeEach
    public void setup() {
        booksService = new BooksService();
        booksService.setBooksRepository(booksRepository);
    }

    @Test
    void testFindById() {

        Book sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setTitle("Sample Book");

        Mockito.when(booksRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        booksService.setBooksRepository(booksRepository);

        Optional<Book> result = booksService.findById(1L);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(sampleBook, result.get());
    }

    @Test
    void testGetAll() {

        Book book1 = new Book(1L, "Title 1", Year.of(2022), "Description 1", "Cover 1");
        Book book2 = new Book(2L, "Title 2", Year.of(2023), "Description 2", "Cover 2");
        List<Book> books = Arrays.asList(book1, book2);

        when(booksRepository.findAll()).thenReturn(books);

        booksService.setBooksRepository(booksRepository);

        List<Book> result = booksService.getAll();
        assertEquals(books, result);
    }

    @Test
    public void testSave() {
        Book sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setTitle("Sample Book");

        Mockito.when(booksRepository.save(sampleBook)).thenReturn(sampleBook);

        booksService.save(sampleBook);

        // In Mockito, the verify() method is used to assert that a certain method has been called
        // on a mocked object with specific arguments. It allows you to verify the interactions
        // between the test code and the mocked object.
        Mockito.verify(booksRepository, Mockito.times(1)).save(sampleBook);
    }

    @Test
    public void testDelete() {
        Book sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setTitle("Sample Book");

        Mockito.when(booksRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        booksService.delete(1L);

        Mockito.verify(booksRepository, Mockito.times(1)).deleteById(1L);
    }
}