package com.example.library.service.Interface;

import com.example.library.entity.Book;
import com.example.library.entity.Comment;
import com.example.library.entity.Review;
import com.example.library.repository.Interface.ReviewRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    void setReviewRepository(ReviewRepository reviewRepository);

    Optional<Review> findById(long id);

    List<Review> getAll();

    Review save(Review review);

    void deleteById(long id);

    @Cacheable(value = "averageRating", key = "#book.id")
    Double getAverageRatingForBook(Book book);

    @CacheEvict(value = "averageRating", key = "#book.id")
    void updateReviewRating(Book book, Double rating);

    List<Comment> getCommentsForReview(Review review);

    List<Review> getReviewsForBook(Book book);
}
