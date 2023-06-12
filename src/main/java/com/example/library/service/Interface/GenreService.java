package com.example.library.service.Interface;

import com.example.library.entity.Genre;
import com.example.library.repository.Interface.GenreRepository;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    void setGenreRepository(GenreRepository genreRepository);

    Optional<Genre> findById(long id);

    List<Genre> getAll();

    Genre save(Genre genre);

    void deleteById(long id);
}
