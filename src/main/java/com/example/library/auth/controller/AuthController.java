package com.example.library.auth.controller;

import com.example.library.auth.entity.AuthenticationRequest;
import com.example.library.auth.entity.AuthenticationResponse;
import com.example.library.auth.entity.RegisterRequest;
import com.example.library.auth.service.AuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")

public class AuthController {
    Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationServiceImpl authenticationService;

    public AuthController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Register user ")
    @ApiResponse(responseCode = "201", description = "OK")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        log.info("Getting register user");
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (Exception e) {
            log.error("User not registered");
            throw e;
        }
    }

    @Operation()
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "User not found")
    @PostMapping("/authenticate")

    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        log.info("Getting authenticate user");
        try {
            return ResponseEntity.ok(authenticationService.authenticated(request));
        } catch (Exception e) {
            log.error("User not authenticated");
            throw e;
        }

    }
}