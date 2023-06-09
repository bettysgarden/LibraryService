package com.example.library.service.Implement;

import com.example.library.entity.Review;
import com.example.library.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ReviewService implements com.example.library.service.Interface.ReviewService {
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void setreviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> findById(long id) {
        logger.debug("inside findById() method");
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> getAll() {
        logger.debug("inside getAll() method");
        return reviewRepository.findAll();
    }

    @Override
    public void save(Review review) {
        logger.debug("inside save() method");
        reviewRepository.save(review);
    }

    @Override
    public void delete(long id) {
        logger.debug("inside delete() method");
        reviewRepository.deleteById(id);
    }

}
