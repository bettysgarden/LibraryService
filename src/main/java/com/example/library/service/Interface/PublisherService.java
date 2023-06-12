package com.example.library.service.Interface;

import com.example.library.entity.Publisher;
import com.example.library.repository.Interface.PublisherRepository;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    void setPublisherRepository(PublisherRepository publisherRepository);

    Optional<Publisher> findById(long id);

    List<Publisher> getAll();

    Publisher save(Publisher publisher);

    void deleteById(long id);
}
