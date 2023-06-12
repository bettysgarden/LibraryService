package com.example.library.controller.Interface;

import com.example.library.entity.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface CommentController {
    @GetMapping
    ResponseEntity<List<Comment>> getAllComments();

    @GetMapping("/{id}")
    ResponseEntity<Optional<Comment>> getCommentById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Comment> createComment(@RequestBody Comment comment);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteComment(@PathVariable Long id);
}
