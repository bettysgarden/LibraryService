package com.example.library.controller;

import com.example.library.controller.Implement.GenreControllerImpl;
import com.example.library.service.Implement.GenreServiceImpl;
import org.junit.jupiter.api.Test;

import com.example.library.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GenreControllerImplTest {

    private MockMvc mockMvc;

    @Mock
    private GenreServiceImpl genreServiceImpl;
    @InjectMocks
    private GenreControllerImpl genreControllerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        genreControllerImpl = new GenreControllerImpl(genreServiceImpl);
        mockMvc = MockMvcBuilders.standaloneSetup(genreControllerImpl).build();
    }

    @Test
    void testGetAllGenres() throws Exception {
        // Prepare
        List<Genre> genres = new ArrayList<>();

        genres.add(new Genre(1L, "Fiction"));
        genres.add(new Genre(2L, "Mystery"));
        when(genreServiceImpl.getAll()).thenReturn(genres);

        // Execute and Verify
        mockMvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Fiction"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Mystery"));
        verify(genreServiceImpl, times(1)).getAll();
    }

    @Test
    void testGetGenreById_ExistingId() throws Exception {
        // Prepare
        long genreId = 1;
        Genre genre = new Genre(genreId, "Fiction");
        when(genreServiceImpl.findById(genreId)).thenReturn(Optional.of(genre));

        // Execute and Verify
        mockMvc.perform(get("/api/genres/{id}", genreId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(genreId))
                .andExpect(jsonPath("$.title").value("Fiction"));
        verify(genreServiceImpl, times(1)).findById(genreId);
    }

    @Test
    void testGetGenreById_NonExistingId() throws Exception {
        // Prepare
        long genreId = 100;
        when(genreServiceImpl.findById(genreId)).thenReturn(Optional.empty());

        // Execute and Verify
        mockMvc.perform(get("/api/genres/{id}", genreId))
                .andExpect(status().isNotFound());
        verify(genreServiceImpl, times(1)).findById(genreId);
    }

    @Test
    void testCreateGenre() throws Exception {
        // Prepare
        Genre genre = new Genre(null, "Fiction");
        Genre createdGenre = new Genre(1L, "Fiction");
        when(genreServiceImpl.save(any(Genre.class))).thenReturn(createdGenre);

        // Execute and Verify
        mockMvc.perform(post("/api/genres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Fiction\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Fiction"));
        verify(genreServiceImpl, times(1)).save(any(Genre.class));
    }

    @Test
    void testDeleteGenre_ExistingId() throws Exception {
        // Prepare
        long genreId = 1;

        // Execute and Verify
        mockMvc.perform(delete("/api/genres/{id}", genreId))
                .andExpect(status().isNoContent());
        verify(genreServiceImpl, times(1)).deleteById(genreId);
    }

    @Test
    void testDeleteGenre_NonExistingId() throws Exception {
        // Prepare
        long genreId = 100;
        doThrow(IllegalArgumentException.class).when(genreServiceImpl).deleteById(genreId);

        // Execute and Verify
        mockMvc.perform(delete("/api/genres/{id}", genreId))
                .andExpect(status().isInternalServerError());
        verify(genreServiceImpl, times(1)).deleteById(genreId);
    }
}
