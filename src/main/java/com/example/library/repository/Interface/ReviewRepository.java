package com.example.library.repository.Interface;

import com.example.library.entity.Book;
import com.example.library.entity.Review;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Cacheable(value = "averageRating", key = "#book.id")
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.book = :book")
    Double getAverageRatingForBook(@Param("book") Book book);

    @CacheEvict(value = "averageRating", key = "#book.id")
    @Query("UPDATE Review r SET r.rating = :rating WHERE r.book = :book")
    void updateReviewRating(@Param("book") Book book, @Param("rating") Double rating);

    @Query("SELECT r FROM Comment r WHERE r.review = :review")
    List<Comment> getCommentsForReview(@Param("review") Review review);

    @Query("SELECT r FROM Review r WHERE r.book = :book")
    List<Review> getReviewsForBook(@Param("book") Book book);
}

