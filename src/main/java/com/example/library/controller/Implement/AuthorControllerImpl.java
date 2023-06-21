package com.example.library.controller.Implement;

import com.example.library.controller.Interface.AuthorController;
import com.example.library.entity.Author;
import com.example.library.service.Implement.AuthorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Authors", description = "The Author API")
public class AuthorControllerImpl implements AuthorController {
    private final Logger logger = LoggerFactory.getLogger(AuthorControllerImpl.class);

    private final AuthorServiceImpl authorServiceImpl;

    @Autowired
    public AuthorControllerImpl(AuthorServiceImpl authorServiceImpl) {
        this.authorServiceImpl = authorServiceImpl;
    }

    @Operation(summary = "Get all authors", tags = "authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved authors"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        logger.info("Fetching all authors");
        try {
            List<Author> authors = authorServiceImpl.getAll();
            return ResponseEntity.ok(authors);
        } catch (Exception e) {
            logger.error("Error occurred while fetching all authors", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get an author by ID", tags = "authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the author"),
            @ApiResponse(responseCode = "404", description = "Author not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        logger.info("Fetching author with ID: {}", id);
        try {
            Optional<Author> author = authorServiceImpl.findById(id);
            return author.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Error occurred while fetching author with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Create a new author", tags = "authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the author"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        logger.info("Creating a new author: {}", author);
        try {
            Author createdAuthor = authorServiceImpl.save(author);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
        } catch (Exception e) {
            logger.error("Error occurred while creating an author: {}", author, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Delete an author by ID", tags = "authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the author"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        logger.info("Deleting author with ID: {}", id);
        try {
            authorServiceImpl.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error occurred while deleting author with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
