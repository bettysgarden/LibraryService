package com.example.library.controller.Implement;

import com.example.library.controller.Interface.AuthControllerInterface;
import com.example.library.entity.AuthenticationRequest;
import com.example.library.entity.AuthenticationResponse;
import com.example.library.entity.RegisterRequest;
import com.example.library.service.Implement.AuthenticationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController implements AuthControllerInterface {
    private final AuthenticationServiceImpl authenticationServiceImpl;

    public AuthController(AuthenticationServiceImpl authenticationServiceImpl) {
        this.authenticationServiceImpl = authenticationServiceImpl;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationServiceImpl.register(request));

    }
    @Override
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationServiceImpl.authenticated(request));
    }

}