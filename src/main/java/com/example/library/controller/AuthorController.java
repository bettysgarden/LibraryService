package com.example.library.controller;

import com.example.library.service.Implement.AuthorService;
import com.example.library.service.Implement.BooksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);


    @Autowired
    private AuthorService authorService;

}
