package com.example.library.controller;

import com.example.library.controller.Implement.CommentControllerImpl;
import com.example.library.service.Implement.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import com.example.library.entity.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CommentControllerImplTest {
    private MockMvc mockMvc;
    @Mock
    private CommentServiceImpl commentServiceImpl;
    @InjectMocks
    private CommentControllerImpl commentControllerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentControllerImpl = new CommentControllerImpl(commentServiceImpl);
        mockMvc = MockMvcBuilders.standaloneSetup(commentControllerImpl).build();
    }

    @Test
    void testGetAllComments() throws Exception {
        // Prepare
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1L, "Nice book!"));
        comments.add(new Comment(2L, "I enjoyed reading it."));
        when(commentServiceImpl.getAll()).thenReturn(comments);

        // Execute and Verify
        mockMvc.perform(get("/api/comments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].content").value("Nice book!"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].content").value("I enjoyed reading it."));
        verify(commentServiceImpl, times(1)).getAll();
    }

    @Test
    void testGetCommentById_ExistingId() throws Exception {
        // Prepare
        long commentId = 1;
        Comment comment = new Comment(commentId, "Nice book!");
        when(commentServiceImpl.findById(commentId)).thenReturn(Optional.of(comment));

        // Execute and Verify
        mockMvc.perform(get("/api/comments/{id}", commentId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(commentId))
                .andExpect(jsonPath("$.content").value("Nice book!"));
        verify(commentServiceImpl, times(1)).findById(commentId);
    }

    @Test
    void testGetCommentById_NonExistingId() throws Exception {
        // Prepare
        long commentId = 100;
        when(commentServiceImpl.findById(commentId)).thenReturn(Optional.empty());

        // Execute and Verify
        mockMvc.perform(get("/api/comments/{id}", commentId))
                .andExpect(status().isNotFound());
        verify(commentServiceImpl, times(1)).findById(commentId);
    }

    @Test
    void testCreateComment() throws Exception {
        // Prepare
        Comment comment = new Comment(null, "Nice book!");
        Comment createdComment = new Comment(1L, "Nice book!");
        when(commentServiceImpl.save(any(Comment.class))).thenReturn(createdComment);

        // Execute and Verify
        mockMvc.perform(post("/api/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\":\"Nice book!\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.content").value("Nice book!"));
        verify(commentServiceImpl, times(1)).save(any(Comment.class));
    }

    @Test
    void testDeleteComment_ExistingId() throws Exception {
        // Prepare
        long commentId = 1;

        // Execute and Verify
        mockMvc.perform(delete("/api/comments/{id}", commentId))
                .andExpect(status().isNoContent());
        verify(commentServiceImpl, times(1)).deleteById(commentId);
    }

    @Test
    void testDeleteComment_NonExistingId() throws Exception {
        // Prepare
        long commentId = 100;
        doThrow(IllegalArgumentException.class).when(commentServiceImpl).deleteById(commentId);

        // Execute and Verify
        mockMvc.perform(delete("/api/comments/{id}", commentId))
                .andExpect(status().isInternalServerError());
        verify(commentServiceImpl, times(1)).deleteById(commentId);
    }
}
