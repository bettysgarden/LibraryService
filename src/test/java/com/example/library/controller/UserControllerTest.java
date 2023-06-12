package com.example.library.controller;

import com.example.library.controller.Implement.UserController;
import com.example.library.entity.User;
import com.example.library.service.Implement.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetAllUsers() {
        // Prepare
        List<User> users = new ArrayList<>();
        users.add(new User(1L, 25, "New York", "john", "password"));
        users.add(new User(2L, 30, "London", "alice", "password"));
        when(userService.getAll()).thenReturn(users);

        // Execute
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<User> responseUsers = response.getBody();
        assertEquals(2, responseUsers.size());
        assertTrue(responseUsers.contains(users.get(0)));
        assertTrue(responseUsers.contains(users.get(1)));
        verify(userService, times(1)).getAll();
    }

    @Test
    void getUserById_NotFound() throws Exception {
        Long userId = 1L;

        when(userService.findById(userId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).findById(userId);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void testCreateUser() {
        // Prepare
        User user = new User(null, 25, "New York", "john", "password");
        User createdUser = new User(1L, 25, "New York", "john", "password");
        when(userService.save(user)).thenReturn(createdUser);

        // Execute
        ResponseEntity<User> response = userController.createUser(user);

        // Verify
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        User responseBody = response.getBody();
        assertEquals(createdUser, responseBody);
        verify(userService, times(1)).save(user);
    }

    @Test
    void testDeleteUser_ExistingId() {
        // Prepare
        Long userId = 1L;

        // Execute
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Verify
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUser_NonExistingId() {
        // Prepare
        Long userId = 100L;
        doThrow(new IllegalArgumentException()).when(userService).deleteById(userId);

        // Execute
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Verify
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(userService, times(1)).deleteById(userId);
    }
}
