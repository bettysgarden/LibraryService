package com.example.library.service;

import com.example.library.entity.Review;
import com.example.library.repository.Interface.ReviewRepository;
import com.example.library.service.Implement.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById() {
        Long reviewId = 1L;
        Review review = new Review(5,"Example Review");
        Optional<Review> optionalReview = Optional.of(review);

        when(reviewRepository.findById(reviewId)).thenReturn(optionalReview);

        Optional<Review> result = reviewService.findById(reviewId);

        assertEquals(optionalReview, result);
        verify(reviewRepository, times(1)).findById(reviewId);
        verifyNoMoreInteractions(reviewRepository);
    }

    @Test
    void getAll() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(5, "Review 1"));
        reviews.add(new Review(5, "Review 2"));

        when(reviewRepository.findAll()).thenReturn(reviews);

        List<Review> result = reviewService.getAll();

        assertEquals(reviews, result);
        verify(reviewRepository, times(1)).findAll();
        verifyNoMoreInteractions(reviewRepository);
    }

    @Test
    void save() {
        Review review = new Review(5, "Example Review");

        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review result = reviewService.save(review);

        assertEquals(review, result);
        verify(reviewRepository, times(1)).save(review);
        verifyNoMoreInteractions(reviewRepository);
    }

    @Test
    void deleteById() {
        Long reviewId = 1L;

        doNothing().when(reviewRepository).deleteById(reviewId);

        reviewService.deleteById(reviewId);

        verify(reviewRepository, times(1)).deleteById(reviewId);
        verifyNoMoreInteractions(reviewRepository);
    }
}
