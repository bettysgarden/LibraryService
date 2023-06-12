package com.example.library.controller.Interface;

import com.example.library.entity.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ReviewController {
    @GetMapping
    ResponseEntity<List<Review>> getAllReviews();

    @GetMapping("/{id}")
    ResponseEntity<Optional<Review>> getReviewById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Review> createReview(@RequestBody Review review);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteReview(@PathVariable Long id);
}
