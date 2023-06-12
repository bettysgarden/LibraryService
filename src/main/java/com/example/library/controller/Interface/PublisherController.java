package com.example.library.controller.Interface;

import com.example.library.entity.Publisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface PublisherController {
    @GetMapping
    ResponseEntity<List<Publisher>> getAllPublishers();

    @GetMapping("/{id}")
    ResponseEntity<Optional<Publisher>> getPublisherById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePublisher(@PathVariable Long id);
}
