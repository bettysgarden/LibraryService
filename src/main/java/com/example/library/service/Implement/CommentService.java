package com.example.library.service.Implement;

import com.example.library.entity.Comment;
import com.example.library.repository.Interface.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements com.example.library.service.Interface.CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Comment> findById(long id) {
        logger.debug("inside findById() method");
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> getAll() {
        logger.debug("inside getAll() method");
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        logger.debug("inside save() method");
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public void deleteById(long id) {
        logger.debug("inside delete() method");
        commentRepository.deleteById(id);
    }
}
