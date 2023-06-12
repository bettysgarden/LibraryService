package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.Interface.BooksRepository;
import com.example.library.service.Implement.BooksServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BooksServiceImplTest {
    @Mock
    private BooksRepository booksRepository;
    private BooksServiceImpl booksServiceImpl;

    @BeforeEach
    public void setup() {
        booksServiceImpl = new BooksServiceImpl();
        booksServiceImpl.setBooksRepository(booksRepository);
    }

    @Test
    void testFindById() {

        Book sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setTitle("Sample Book");

        // Настройка поведения заглушки
        // вы можете указать, что заглушка должна вернуть определенное значение при вызове определенного метода
        Mockito.when(booksRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        booksServiceImpl.setBooksRepository(booksRepository);

        Optional<Book> result = booksServiceImpl.findById(1L);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(sampleBook, result.get());
    }

    @Test
    void testGetAll() {

        Book book1 = new Book(1L, "Title 1", 2023, "Description 1", "Cover 1");
        Book book2 = new Book(2L, "Title 2", 2023, "Description 2", "Cover 2");
        List<Book> books = Arrays.asList(book1, book2);

        when(booksRepository.findAll()).thenReturn(books);

        booksServiceImpl.setBooksRepository(booksRepository);

        List<Book> result = booksServiceImpl.getAll();
        assertEquals(books, result);
    }

    @Test
    public void testSave() {
        Book sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setTitle("Sample Book");

        Mockito.when(booksRepository.save(sampleBook)).thenReturn(sampleBook);

        booksServiceImpl.save(sampleBook);

        // Проверка вызовов: Mockito также предоставляет возможность проверить,
        // какие методы заглушки были вызваны и с какими параметрами
        Mockito.verify(booksRepository, Mockito.times(1)).save(sampleBook);
    }

    @Test
    public void testDelete() {
        Book sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setTitle("Sample Book");

        Mockito.when(booksRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        booksServiceImpl.deleteById(1L);

        Mockito.verify(booksRepository, Mockito.times(1)).deleteById(1L);
    }
}