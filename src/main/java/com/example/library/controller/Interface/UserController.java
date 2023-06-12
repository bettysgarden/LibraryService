package com.example.library.controller.Interface;

import com.example.library.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface UserController {
    @GetMapping
    ResponseEntity<List<User>> getAllUsers();

    @GetMapping("/{id}")
    ResponseEntity<Optional<User>> getUserById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}
