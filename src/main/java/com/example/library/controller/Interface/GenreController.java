package com.example.library.controller.Interface;

import com.example.library.entity.Genre;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface GenreController {
    @GetMapping
    ResponseEntity<List<Genre>> getAllGenres();

    @GetMapping("/{id}")
    ResponseEntity<Optional<Genre>> getGenreById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Genre> createGenre(@RequestBody Genre genre);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteGenre(@PathVariable Long id);
}
