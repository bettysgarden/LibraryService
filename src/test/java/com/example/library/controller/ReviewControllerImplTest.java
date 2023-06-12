package com.example.library.controller;


import com.example.library.controller.Implement.ReviewControllerImpl;
import com.example.library.entity.Review;
import com.example.library.service.Implement.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReviewControllerImplTest {

    private MockMvc mockMvc;

    @Mock
    private ReviewServiceImpl reviewServiceImpl;
    @InjectMocks
    private ReviewControllerImpl reviewControllerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reviewControllerImpl = new ReviewControllerImpl(reviewServiceImpl);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewControllerImpl).build();
    }

    @Test
    void testGetAllReviews() throws Exception {
        // Prepare
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(1L, 5.0, "Good book"));
        reviews.add(new Review(2L, 4.0, "Interesting read"));
        when(reviewServiceImpl.getAll()).thenReturn(reviews);

        // Execute and Verify
        mockMvc.perform(get("/api/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].content").value("Good book"))
                .andExpect(jsonPath("$[0].rating").value(5))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].content").value("Interesting read"))
                .andExpect(jsonPath("$[1].rating").value(4));
        verify(reviewServiceImpl, times(1)).getAll();
    }

    @Test
    void testGetReviewById_ExistingId() throws Exception {
        // Prepare
        Long reviewId = 1L;
        Review review = new Review(reviewId, 5.0, "Good book");
        when(reviewServiceImpl.findById(reviewId)).thenReturn(Optional.of(review));

        // Execute and Verify
        mockMvc.perform(get("/api/reviews/{id}", reviewId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(reviewId))
                .andExpect(jsonPath("$.content").value("Good book"))
                .andExpect(jsonPath("$.rating").value(5));
        verify(reviewServiceImpl, times(1)).findById(reviewId);
    }

    @Test
    void testGetReviewById_NonExistingId() throws Exception {
        // Prepare
        long reviewId = 100;
        when(reviewServiceImpl.findById(reviewId)).thenReturn(Optional.empty());

        // Execute and Verify
        mockMvc.perform(get("/api/reviews/{id}", reviewId))
                .andExpect(status().isNotFound());
        verify(reviewServiceImpl, times(1)).findById(reviewId);
    }

    @Test
    void testCreateReview() throws Exception {
        // Prepare
        Review review = new Review(null, 5.0, "Good book");
        Review createdReview = new Review(1L, 5.0, "Good book");
        when(reviewServiceImpl.save(any(Review.class))).thenReturn(createdReview);

        // Execute and Verify
        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"comment\":\"Good book\",\"rating\":5}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.content").value("Good book"))
                .andExpect(jsonPath("$.rating").value(5));
        verify(reviewServiceImpl, times(1)).save(any(Review.class));
    }

    @Test
    void testDeleteReview_ExistingId() throws Exception {
        // Prepare
        long reviewId = 1;

        // Execute and Verify
        mockMvc.perform(delete("/api/reviews/{id}", reviewId))
                .andExpect(status().isNoContent());
        verify(reviewServiceImpl, times(1)).deleteById(reviewId);
    }

    @Test
    void testDeleteReview_NonExistingId() throws Exception {
        // Prepare
        long reviewId = 100;
        doThrow(IllegalArgumentException.class).when(reviewServiceImpl).deleteById(reviewId);

        // Execute and Verify
        mockMvc.perform(delete("/api/reviews/{id}", reviewId))
                .andExpect(status().isInternalServerError());
        verify(reviewServiceImpl, times(1)).deleteById(reviewId);
    }
}

