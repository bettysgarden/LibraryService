package com.example.library.controller.Implement;

import com.example.library.entity.Publisher;
import com.example.library.service.Implement.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController implements com.example.library.controller.Interface.PublisherController {

    private final PublisherService publisherService;
    private final Logger logger = LoggerFactory.getLogger(PublisherController.class);

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        logger.info("Getting all publishers");
        try {
            List<Publisher> publishers = publisherService.getAll();
            return ResponseEntity.ok(publishers);
        } catch (Exception e) {
            logger.error("Error occurred while getting all publishers", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Publisher>> getPublisherById(@PathVariable Long id) {
        logger.info("Getting publisher by ID: {}", id);
        try {
            Optional<Publisher> publisher = publisherService.findById(id);
            if (publisher.isPresent()) {
                return ResponseEntity.ok(publisher);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error occurred while getting publisher by ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        logger.info("Creating a new publisher: {}", publisher);
        try {
            Publisher createdPublisher = publisherService.save(publisher);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPublisher);
        } catch (Exception e) {
            logger.error("Error occurred while creating a publisher: {}", publisher, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        logger.info("Deleting publisher with ID: {}", id);
        try {
            publisherService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error occurred while deleting publisher with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
