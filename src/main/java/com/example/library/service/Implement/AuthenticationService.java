package com.example.library.service.Implement;

import com.example.library.entity.AuthenticationRequest;
import com.example.library.entity.AuthenticationResponse;
import com.example.library.entity.RegisterRequest;
import com.example.library.repository.Interface.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request){
        var user = com.example.library.entity.User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        var jwtToken=jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticated(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user =userRepository.findUserByUsername(request.getUsername()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
