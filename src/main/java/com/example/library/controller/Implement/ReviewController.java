package com.example.library.controller.Implement;

import com.example.library.entity.Review;
import com.example.library.service.Implement.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController implements com.example.library.controller.Interface.ReviewController {

    private final ReviewService reviewService;
    private final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        logger.info("Getting all reviews");
        try {
            List<Review> reviews = reviewService.getAll();
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            logger.error("Error occurred while getting all reviews", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Review>> getReviewById(@PathVariable Long id) {
        logger.info("Getting review by ID: {}", id);
        try {
            Optional<Review> review = reviewService.findById(id);
            if (review.isPresent()) {
                return ResponseEntity.ok(review);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error occurred while getting review by ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        logger.info("Creating a new review: {}", review);
        try {
            Review createdReview = reviewService.save(review);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
        } catch (Exception e) {
            logger.error("Error occurred while creating a review: {}", review, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        logger.info("Deleting review with ID: {}", id);
        try {
            reviewService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error occurred while deleting review with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
