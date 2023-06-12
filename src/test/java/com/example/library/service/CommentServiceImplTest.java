package com.example.library.service;

import com.example.library.entity.Comment;
import com.example.library.repository.Interface.CommentRepository;
import com.example.library.service.Implement.CommentServiceImpl;
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

class CommentServiceImplTest {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById() {
        Long commentId = 1L;
        Comment comment = new Comment("Comment 1");
        Optional<Comment> optionalComment = Optional.of(comment);

        when(commentRepository.findById(commentId)).thenReturn(optionalComment);

        Optional<Comment> result = commentServiceImpl.findById(commentId);

        assertEquals(optionalComment, result);
        verify(commentRepository, times(1)).findById(commentId);
        verifyNoMoreInteractions(commentRepository);
    }

    @Test
    void getAll() {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment("Comment 1"));
        comments.add(new Comment("Comment 2"));

        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> result = commentServiceImpl.getAll();

        assertEquals(comments, result);
        verify(commentRepository, times(1)).findAll();
        verifyNoMoreInteractions(commentRepository);
    }

    @Test
    void save() {
        Comment comment = new Comment("Comment 1");

        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        Comment result = commentServiceImpl.save(comment);

        assertEquals(comment, result);
        verify(commentRepository, times(1)).save(comment);
        verifyNoMoreInteractions(commentRepository);
    }

    @Test
    void deleteById() {
        Long commentId = 1L;

        doNothing().when(commentRepository).deleteById(commentId);

        commentServiceImpl.deleteById(commentId);

        verify(commentRepository, times(1)).deleteById(commentId);
        verifyNoMoreInteractions(commentRepository);
    }
}
