package com.example.library.controller;

import com.example.library.service.Implement.BooksService;
import com.example.library.service.Implement.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller

public class GenreController {
    private static final Logger logger = LoggerFactory.getLogger(GenreService.class);


    @Autowired
    private GenreService genreService;
}
