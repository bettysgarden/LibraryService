//package com.example.library.service;
//
//import com.example.library.enums.Role;
//import com.example.library.service.Implement.JwtService;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.security.Key;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//public class JwtServiceTest {
//
//    private static final String SECRET_KEY = "JGGXaZOqcaGK0z5866ERoHNeSpUjDkpiPitoalyVb27uyMog8qCpdrxzqH8JGJB/";
//
//    @InjectMocks
//    private JwtService jwtService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testExtractUsername() {
//        String token = generateToken("username");
//        String extractedUsername = jwtService.extractUsername(token);
//        assertEquals("username", extractedUsername);
//    }
//
//    @Test
//    public void testGenerateToken() {
//        User userDetails = createUserDetails("username", "password", String.valueOf(Role.User));
//        String token = jwtService.generateToken(userDetails);
//        assertNotNull(token);
//    }
//
//    @Test
//    public void testIsTokenValid() {
//        User userDetails = createUserDetails("username", "password", String.valueOf(Role.User));
//        String token = generateToken("username");
//        boolean isValid = jwtService.isTokenValid(token, userDetails);
//        assertTrue(isValid);
//    }
//
//    private String generateToken(String subject) {
//        Map<String, Object> claims = new HashMap<>();
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    private Key getSignKey() {
//        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//    }
//
//    private User createUserDetails(String username, String password, String role) {
//        Role userRole = Role.valueOf(role); // Convert the role String to Role enum
//        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userRole.name()));
//        return new User(username, password, authorities);
//    }
//
//}
