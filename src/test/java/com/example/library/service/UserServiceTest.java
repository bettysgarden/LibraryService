package com.example.library.service;

import com.example.library.entity.User;
import com.example.library.repository.Interface.UserRepository;
import com.example.library.service.Implement.UserService;
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

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById() {
        long userId = 1;
        User user = new User(12, "NYC", "username", "password");
        Optional<User> optionalUser = Optional.of(user);

        when(userRepository.findById(userId)).thenReturn(optionalUser);

        Optional<User> result = userService.findById(userId);

        assertEquals(optionalUser, result);
        verify(userRepository, times(1)).findById(userId);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void getAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(12, "NYC", "beatrix", "password"));
        users.add(new User(12, "NYC", "username", "password"));

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAll();

        assertEquals(users, result);
        verify(userRepository, times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void save() {
        User user = new User(12, "NYC", "username", "password");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.save(user);

        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void deleteById() {
        long userId = 1;

        doNothing().when(userRepository).deleteById(userId);

        userService.deleteById(userId);

        verify(userRepository, times(1)).deleteById(userId);
        verifyNoMoreInteractions(userRepository);
    }
}
