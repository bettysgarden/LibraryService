package com.example.library.service.Interface;

import com.example.library.entity.Review;
import com.example.library.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    void setreviewRepository(ReviewRepository reviewRepository);

    Optional<Review> findById(long id);

    List<Review> getAll();

    void save(Review review);

    void delete(long id);
}
