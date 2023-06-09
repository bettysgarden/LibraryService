package com.example.library.controller;

import com.example.library.service.Implement.BooksService;
import com.example.library.service.Implement.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller

public class ReviewController {
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    @Autowired
    private ReviewService reviewService;
}
