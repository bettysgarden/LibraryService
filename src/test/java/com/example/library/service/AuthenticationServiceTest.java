package com.example.library.service;

import com.example.library.entity.AuthenticationRequest;
import com.example.library.entity.AuthenticationResponse;
import com.example.library.entity.RegisterRequest;
import com.example.library.enums.Role;
import com.example.library.repository.Interface.UserRepository;
import com.example.library.service.Implement.AuthenticationServiceImpl;
import com.example.library.service.Implement.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() {
        // Mock the necessary dependencies
        RegisterRequest registerRequest = new RegisterRequest("username", "password", Role.User);
        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any())).thenReturn(null);
        when(jwtService.generateToken(any())).thenReturn("jwtToken");

        // Call the method under test
        AuthenticationResponse response = authenticationService.register(registerRequest);

        // Assert the result
        assertEquals("jwtToken", response.getToken());
    }

}
