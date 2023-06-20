package com.example.library.service.Interface;

import com.example.library.entity.User;
import com.example.library.repository.Interface.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void setUserRepository(UserRepository userRepository);

    Optional<User> findById(long id);
    User findByUsername(String username);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<User> getAll();

    User save(User user);

    void deleteById(long id);
}
