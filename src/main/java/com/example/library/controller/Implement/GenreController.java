package com.example.library.controller.Implement;

import com.example.library.entity.Genre;
import com.example.library.service.Implement.GenreService;
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
public class GenreController implements com.example.library.controller.Interface.GenreController {

    private final GenreService genreService;
    private final Logger logger = LoggerFactory.getLogger(GenreController.class);

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        logger.info("Getting all genres");
        try {
            List<Genre> genres = genreService.getAll();
            return ResponseEntity.ok(genres);
        } catch (Exception e) {
            logger.error("Error occurred while getting all genres", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Genre>> getGenreById(@PathVariable Long id) {
        logger.info("Getting genre by ID: {}", id);
        try {
            Optional<Genre> genre = genreService.findById(id);
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

    @Override
    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        logger.info("Creating a new genre: {}", genre);
        try {
            Genre createdGenre = genreService.save(genre);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
        } catch (Exception e) {
            logger.error("Error occurred while creating a genre: {}", genre, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        logger.info("Deleting genre with ID: {}", id);
        try {
            genreService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error occurred while deleting genre with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
