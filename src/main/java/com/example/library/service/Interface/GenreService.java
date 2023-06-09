package com.example.library.service.Interface;

import com.example.library.entity.Genre;
import com.example.library.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    void setGenreRepository(GenreRepository genreRepository);

    Optional<Genre> findById(long id);

    List<Genre> getAll();

    void save(Genre genre);

    void delete(long id);
}
