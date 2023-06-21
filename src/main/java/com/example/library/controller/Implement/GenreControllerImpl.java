package com.example.library.controller.Implement;

import com.example.library.controller.Interface.GenreController;
import com.example.library.entity.Genre;
import com.example.library.service.Implement.GenreServiceImpl;
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
@RequestMapping("/api/genres")
@Tag(name = "Genres", description = "The Genre API")
public class GenreControllerImpl implements GenreController {

    private final GenreServiceImpl genreServiceImpl;
    private final Logger logger = LoggerFactory.getLogger(GenreControllerImpl.class);

    @Autowired
    public GenreControllerImpl(GenreServiceImpl genreServiceImpl) {
        this.genreServiceImpl = genreServiceImpl;
    }

    @Operation(summary = "Get all genres", tags = "genres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved genres"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        logger.info("Getting all genres");
        try {
            List<Genre> genres = genreServiceImpl.getAll();
            return ResponseEntity.ok(genres);
        } catch (Exception e) {
            logger.error("Error occurred while getting all genres", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get a genre by ID", tags = "genres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the genre"),
            @ApiResponse(responseCode = "404", description = "Genre not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Genre>> getGenreById(@PathVariable Long id) {
        logger.info("Getting genre by ID: {}", id);
        try {
            Optional<Genre> genre = genreServiceImpl.findById(id);
            if (genre.isPresent()) {
                return ResponseEntity.ok(genre);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error occurred while getting genre by ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Create a new genre", tags = "genres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the genre"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        logger.info("Creating a new genre: {}", genre);
        try {
            Genre createdGenre = genreServiceImpl.save(genre);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
        } catch (Exception e) {
            logger.error("Error occurred while creating a genre: {}", genre, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Delete a genre by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the genre"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        logger.info("Deleting genre with ID: {}", id);
        try {
            genreServiceImpl.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error occurred while deleting genre with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
