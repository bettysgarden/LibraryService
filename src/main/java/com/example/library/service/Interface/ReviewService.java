package com.example.library.service.Interface;

import com.example.library.entity.Review;
import com.example.library.repository.Interface.ReviewRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    void setReviewRepository(ReviewRepository reviewRepository);

    Optional<Review> findById(long id);

    List<Review> getAll();

    Review save(Review review);

    void deleteById(long id);
}
