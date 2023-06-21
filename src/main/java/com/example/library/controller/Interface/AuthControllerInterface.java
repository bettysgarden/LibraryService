package com.example.library.controller.Interface;

import com.example.library.auth.entity.AuthenticationRequest;
import com.example.library.auth.entity.AuthenticationResponse;
import com.example.library.auth.entity.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthControllerInterface {
    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    );

    @PostMapping("/authenticate")
    ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    );
}
