package com.example.library.service;

import com.example.library.entity.Genre;
import com.example.library.repository.Interface.GenreRepository;
import com.example.library.service.Implement.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GenreServiceTest {
    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById() {
        Long genreId = 1L;
        Genre genre = new Genre("Thriller");
        Optional<Genre> optionalGenre = Optional.of(genre);

        when(genreRepository.findById(genreId)).thenReturn(optionalGenre);

        Optional<Genre> result = genreService.findById(genreId);

        assertEquals(optionalGenre, result);
        verify(genreRepository, times(1)).findById(genreId);
        verifyNoMoreInteractions(genreRepository);
    }

    @Test
    void getAll() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre("Thriller"));
        genres.add(new Genre("Romance"));

        when(genreRepository.findAll()).thenReturn(genres);

        List<Genre> result = genreService.getAll();

        assertEquals(genres, result);
        verify(genreRepository, times(1)).findAll();
        verifyNoMoreInteractions(genreRepository);
    }

    @Test
    void save() {
        Genre genre = new Genre("Thriller");

        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        Genre result = genreService.save(genre);

        assertEquals(genre, result);
        verify(genreRepository, times(1)).save(genre);
        verifyNoMoreInteractions(genreRepository);
    }

    @Test
    void deleteById() {
        Long genreId = 1L;

        doNothing().when(genreRepository).deleteById(genreId);

        genreService.deleteById(genreId);

        verify(genreRepository, times(1)).deleteById(genreId);
        verifyNoMoreInteractions(genreRepository);
    }
}

