package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService implements BooksServiceInterface {
    @Autowired
    private BooksRepository booksRepository;

    @Override
    public List<Book> getAll(){
        return booksRepository.findAll();
    }

    @Override
    public void save(Book book){
        booksRepository.save(book);
    }

    @Override
    public void delete(long id){
        booksRepository.deleteById(id);
    }
}
