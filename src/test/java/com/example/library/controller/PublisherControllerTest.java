package com.example.library.controller;

import com.example.library.entity.Publisher;
import com.example.library.service.Implement.PublisherService;
import com.example.library.controller.Implement.PublisherController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

class PublisherControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PublisherService publisherService;

    @InjectMocks
    private PublisherController publisherController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(publisherController).build();
    }

    @Test
    void getAllPublishers() throws Exception {
        List<Publisher> publishers = new ArrayList<>();
        publishers.add(new Publisher(1L, "Publisher 1"));
        publishers.add(new Publisher(2L, "Publisher 2"));

        when(publisherService.getAll()).thenReturn(publishers);

        mockMvc.perform(get("/api/publishers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Publisher 1"))
                .andExpect(jsonPath("$[1].title").value("Publisher 2"));

        verify(publisherService, times(1)).getAll();
        verifyNoMoreInteractions(publisherService);
    }

    @Test
    void getPublisherById() throws Exception {
        long publisherId = 1;
        Publisher publisher = new Publisher("Publisher 1");

        when(publisherService.findById(publisherId)).thenReturn(Optional.of(publisher));

        mockMvc.perform(get("/api/publishers/{id}", publisherId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Publisher 1"));

        verify(publisherService, times(1)).findById(publisherId);
        verifyNoMoreInteractions(publisherService);
    }

    @Test
    void getPublisherById_NotFound() throws Exception {
        long publisherId = 1;

        when(publisherService.findById(publisherId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/publishers/{id}", publisherId))
                .andExpect(status().isNotFound());

        verify(publisherService, times(1)).findById(publisherId);
        verifyNoMoreInteractions(publisherService);
    }

    @Test
    void createPublisher() throws Exception {
        Publisher publisher = new Publisher("Publisher 1");
        Publisher savedPublisher = new Publisher(1L, "Publisher 1");

        when(publisherService.save(any(Publisher.class))).thenReturn(savedPublisher);

        mockMvc.perform(post("/api/publishers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Publisher 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Publisher 1"));

        verify(publisherService, times(1)).save(any(Publisher.class));
        verifyNoMoreInteractions(publisherService);
    }

    @Test
    void deletePublisher() throws Exception {
        long publisherId = 1;

        mockMvc.perform(delete("/api/publishers/{id}", publisherId))
                .andExpect(status().isNoContent());

        verify(publisherService, times(1)).deleteById(publisherId);
        verifyNoMoreInteractions(publisherService);
    }
}

