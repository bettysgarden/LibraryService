package com.example.library.controller.Implement;
import com.example.library.controller.Interface.PublisherController;
import com.example.library.entity.Publisher;
import com.example.library.service.Implement.PublisherServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Publishers", description = "The Publisher API")
public class PublisherControllerImpl implements PublisherController {

    private final PublisherServiceImpl publisherServiceImpl;
    private final Logger logger = LoggerFactory.getLogger(PublisherControllerImpl.class);

    @Autowired
    public PublisherControllerImpl(PublisherServiceImpl publisherServiceImpl) {
        this.publisherServiceImpl = publisherServiceImpl;
    }

    @Operation(summary = "Get all publishers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved publishers"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        logger.info("Getting all publishers");
        try {
            List<Publisher> publishers = publisherServiceImpl.getAll();
            return ResponseEntity.ok(publishers);
        } catch (Exception e) {
            logger.error("Error occurred while getting all publishers", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get a publisher by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the publisher"),
            @ApiResponse(responseCode = "404", description = "Publisher not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Publisher>> getPublisherById(@PathVariable Long id) {
        logger.info("Getting publisher by ID: {}", id);
        try {
            Optional<Publisher> publisher = publisherServiceImpl.findById(id);
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

    @Operation(summary = "Create a new publisher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the publisher"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        logger.info("Creating a new publisher: {}", publisher);
        try {
            Publisher createdPublisher = publisherServiceImpl.save(publisher);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPublisher);
        } catch (Exception e) {
            logger.error("Error occurred while creating a publisher: {}", publisher, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Delete a publisher by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the publisher"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        logger.info("Deleting publisher with ID: {}", id);
        try {
            publisherServiceImpl.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error occurred while deleting publisher with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

