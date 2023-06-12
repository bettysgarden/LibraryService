package com.example.library.service;

import com.example.library.entity.Publisher;
import com.example.library.repository.Interface.PublisherRepository;
import com.example.library.service.Implement.PublisherServiceImpl;
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

class PublisherServiceImplTest {
    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherServiceImpl publisherServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById() {
        Long publisherId = 1L;
        Publisher publisher = new Publisher("Example Publisher");
        Optional<Publisher> optionalPublisher = Optional.of(publisher);

        when(publisherRepository.findById(publisherId)).thenReturn(optionalPublisher);

        Optional<Publisher> result = publisherServiceImpl.findById(publisherId);

        assertEquals(optionalPublisher, result);
        verify(publisherRepository, times(1)).findById(publisherId);
        verifyNoMoreInteractions(publisherRepository);
    }

    @Test
    void getAll() {
        List<Publisher> publishers = new ArrayList<>();
        publishers.add(new Publisher("Publisher 1"));
        publishers.add(new Publisher("Publisher 2"));

        when(publisherRepository.findAll()).thenReturn(publishers);

        List<Publisher> result = publisherServiceImpl.getAll();

        assertEquals(publishers, result);
        verify(publisherRepository, times(1)).findAll();
        verifyNoMoreInteractions(publisherRepository);
    }

    @Test
    void save() {
        Publisher publisher = new Publisher("Example Publisher");

        when(publisherRepository.save(any(Publisher.class))).thenReturn(publisher);

        Publisher result = publisherServiceImpl.save(publisher);

        assertEquals(publisher, result);
        verify(publisherRepository, times(1)).save(publisher);
        verifyNoMoreInteractions(publisherRepository);
    }

    @Test
    void deleteById() {
        Long publisherId = 1L;

        doNothing().when(publisherRepository).deleteById(publisherId);

        publisherServiceImpl.deleteById(publisherId);

        verify(publisherRepository, times(1)).deleteById(publisherId);
        verifyNoMoreInteractions(publisherRepository);
    }
}

