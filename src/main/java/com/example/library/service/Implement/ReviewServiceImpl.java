package com.example.library.service.Implement;

import com.example.library.entity.Book;
import com.example.library.entity.Review;
import com.example.library.repository.Interface.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements com.example.library.service.Interface.ReviewService {
    private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Cacheable(value = "reviews", key = "#id")
    @Override
    public Optional<Review> findById(long id) {
        logger.debug("inside findById() method");
        try {
            return reviewRepository.findById(id);
        } catch (Exception e) {
            logger.error("An error occurred while finding a review by ID: " + id, e);
            throw e;
        }
    }

    @Override
    public List<Review> getAll() {
        logger.debug("inside getAll() method");
        try {
            return reviewRepository.findAll();
        } catch (Exception e) {
            logger.error("An error occurred while retrieving all reviews", e);
            throw e;
        }
    }

    @CacheEvict(value = "reviews", key = "#review.id")
    @Override
    public Review save(Review review) {
        logger.debug("inside save() method");
        try {
            return reviewRepository.save(review);
        } catch (Exception e) {
            logger.error("An error occurred while saving the review: " + review, e);
            throw e;
        }
    }

    @CacheEvict(value = "reviews", key = "#id")
    @Override
    public void deleteById(long id) {
        logger.debug("inside delete() method");
        try {
            reviewRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("An error occurred while deleting the review with ID: " + id, e);
            throw e;
        }
    }

    @Override
    public Double getAverageRatingForBook(Book book) {
        logger.debug("inside getAverageRatingForBook() method");
        try {
            return reviewRepository.getAverageRatingForBook(book);
        } catch (Exception e) {
            logger.error("An error occurred while retrieving the average rating for book: " + book.getId(), e);
            throw e;
        }
    }

    @Override
    public void updateReviewRating(Book book, Double rating) {
        logger.debug("inside updateReviewRating() method");
        reviewRepository.updateReviewRating(book, rating);
    }

    @Override
    public List<Review> getReviewsForBook(Book book) {
        try {
            return reviewRepository.getReviewsForBook(book);
        } catch (Exception e) {
            logger.error("Failed to retrieve reviews for book: {}", book.getId(), e);
            throw e;
        }
    }

}

