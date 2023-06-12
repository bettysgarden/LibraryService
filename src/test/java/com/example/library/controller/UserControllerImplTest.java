package com.example.library.controller;

import com.example.library.controller.Implement.UserControllerImpl;
import com.example.library.entity.User;
import com.example.library.service.Implement.UserServiceImpl;
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

class UserControllerImplTest {
    private MockMvc mockMvc;
    @InjectMocks
    private UserControllerImpl userControllerImpl;

    @Mock
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userControllerImpl).build();
    }

    @Test
    void testGetAllUsers() {
        // Prepare
        List<User> users = new ArrayList<>();
        users.add(new User(1L, 25, "New York", "john", "password"));
        users.add(new User(2L, 30, "London", "alice", "password"));
        when(userServiceImpl.getAll()).thenReturn(users);

        // Execute
        ResponseEntity<List<User>> response = userControllerImpl.getAllUsers();

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<User> responseUsers = response.getBody();
        assertEquals(2, responseUsers.size());
        assertTrue(responseUsers.contains(users.get(0)));
        assertTrue(responseUsers.contains(users.get(1)));
        verify(userServiceImpl, times(1)).getAll();
    }

    @Test
    void getUserById_NotFound() throws Exception {
        Long userId = 1L;

        when(userServiceImpl.findById(userId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isNotFound());

        verify(userServiceImpl, times(1)).findById(userId);
        verifyNoMoreInteractions(userServiceImpl);
    }

    @Test
    void testCreateUser() {
        // Prepare
        User user = new User(null, 25, "New York", "john", "password");
        User createdUser = new User(1L, 25, "New York", "john", "password");
        when(userServiceImpl.save(user)).thenReturn(createdUser);

        // Execute
        ResponseEntity<User> response = userControllerImpl.createUser(user);

        // Verify
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        User responseBody = response.getBody();
        assertEquals(createdUser, responseBody);
        verify(userServiceImpl, times(1)).save(user);
    }

    @Test
    void testDeleteUser_ExistingId() {
        // Prepare
        Long userId = 1L;

        // Execute
        ResponseEntity<Void> response = userControllerImpl.deleteUser(userId);

        // Verify
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userServiceImpl, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUser_NonExistingId() {
        // Prepare
        Long userId = 100L;
        doThrow(new IllegalArgumentException()).when(userServiceImpl).deleteById(userId);

        // Execute
        ResponseEntity<Void> response = userControllerImpl.deleteUser(userId);

        // Verify
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(userServiceImpl, times(1)).deleteById(userId);
    }
}
