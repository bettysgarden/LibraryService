package com.example.library.controller;

import com.example.library.entity.Comment;
import com.example.library.service.Interface.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        return commentOptional
                .map(comment -> new ResponseEntity<>(comment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> commentList = commentService.getAll();
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Void> createComment(@RequestBody Comment comment) {
        commentService.save(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable long id, @RequestBody Comment comment) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (commentOptional.isPresent()) {
            comment.setId(id);
            commentService.save(comment);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (commentOptional.isPresent()) {
            commentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

