package com.example.library.service.Implement;

import com.example.library.entity.Book;
import com.example.library.entity.Review;
import com.example.library.repository.Interface.BooksRepository;
import com.example.library.service.Interface.BooksService;
import com.example.library.service.Interface.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {
    private static final Logger logger = LoggerFactory.getLogger(BooksServiceImpl.class);

    private BooksRepository booksRepository;

    private ReviewServiceImpl reviewService;

    @Autowired
    public void setReviewService(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @Autowired
    public void setBooksRepository(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public Optional<Book> findById(long id) {
        logger.debug("inside findById() method");
        try {
            Optional<Book> optionalBook = booksRepository.findById(id);
            if (optionalBook.isPresent()) {
                Book book = optionalBook.get();
                Double averageRating = 0.0;
                    if (!reviewService.getReviewsForBook(book).isEmpty()){
                        averageRating = reviewService.getAverageRatingForBook(book);
                    }
                    book.setRating(averageRating);
                return Optional.of(book);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error("Error occurred while finding book by ID: {}", id, e);
            throw e;
        }
    }


    @Cacheable(value = "books")
    @Override
    public List<Book> getAll() {
        logger.debug("inside getAll() method");
        try {
            List<Book> books = booksRepository.findAll();
            Double averageRating = 0.0;
            for (Book book : books) {
                if (!reviewService.getReviewsForBook(book).isEmpty()){
                    averageRating = reviewService.getAverageRatingForBook(book);
                }
                book.setRating(averageRating);
            }

            return books;
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all books", e);
            throw e;
        }
    }


    @Override
    public Book save(Book book) {
        logger.debug("inside save() method");
        try {
            return booksRepository.save(book);
        } catch (Exception e) {
            logger.error("Error occurred while saving book: {}", book, e);
            throw e;
        }
    }

    @Override
    public void deleteById(long id) {
        logger.debug("inside delete() method");
        try {
            booksRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting book by ID: {}", id, e);
            throw e;
        }
    }
}
