package com.example.library;

import com.example.library.entity.Book;
import com.example.library.repository.BooksRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    BooksRepository booksRepository;

//    @Test
//    public void should_find_no_books_if_repository_is_empty() {
//        List books = booksRepository.findAll();
//
//    }

    @Test
    public void should_store_a_book() {
//        Book book = booksRepository.save(new Book("title", 5, 2002, "description", null));
//        System.out.println(book.toString());
    }

    @Test
    public void should_find_all_books() {
    }

    @Test
    public void should_find_book_by_id() {
    }

    @Test
    public void should_find_published_books() {
    }

    @Test
    public void should_find_books_by_title_containing_string() {
    }

    @Test
    public void should_update_book_by_id() {
    }

    @Test
    public void should_delete_book_by_id() {
    }

    @Test
    public void should_delete_all_books() {
    }
}
