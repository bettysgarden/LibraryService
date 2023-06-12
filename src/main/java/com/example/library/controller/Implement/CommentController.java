package com.example.library.controller.Implement;

import com.example.library.entity.Comment;
import com.example.library.service.Implement.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController implements com.example.library.controller.Interface.CommentController {

    private final CommentService commentService;
    private final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        logger.info("Getting all comments");
        try {
            List<Comment> comments = commentService.getAll();
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            logger.error("Error occurred while getting all comments", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Comment>> getCommentById(@PathVariable Long id) {
        logger.info("Getting comment by ID: {}", id);
        try {
            Optional<Comment> comment = commentService.findById(id);
            if (comment.isPresent()) {
                return ResponseEntity.ok(comment);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error occurred while getting comment by ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        logger.info("Creating a new comment: {}", comment);
        try {
            Comment createdComment = commentService.save(comment);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        } catch (Exception e) {
            logger.error("Error occurred while creating a comment: {}", comment, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        logger.info("Deleting comment with ID: {}", id);
        try {
            commentService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error occurred while deleting comment with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
