package com.example.library.service.Implement;

import com.example.library.entity.User;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.repository.Interface.UserRepository;
import com.example.library.service.Interface.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        logger.debug("inside save() method");
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            logger.error("Error occurred while saving user: {}", user, e);
            throw e;
        }
    }
    @Override
    public Optional<User> findById(long id) {
        logger.debug("inside findById() method");
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error occurred while finding user by ID: {}", id, e);
            throw e;
        }
    }

    private User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Inside loadUserByUsername() method");
        try {
            User user = findUserByUsername(username);

            // Perform the conversion from User to UserDetails
            UserDetails userDetails = convertToUserDetails(user);

            return userDetails;
        } catch (Exception e) {
            logger.error("Error occurred while finding user by username: {}", username, e);
            throw new UsernameNotFoundException("User not found", e);
        }
    }

    private UserDetails convertToUserDetails(User user) {
        // Perform the necessary conversion logic here
        // Create and return an instance of UserDetails, e.g., using org.springframework.security.core.userdetails.User

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("ROLE_USER")  // Set the user roles as needed
                .build();

        return userDetails;
    }


    @Override
    public List<User> getAll() {
        logger.debug("inside getAll() method");
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all users", e);
            throw e;
        }
    }


    @Override
    public void deleteById(long id) {
        logger.debug("inside delete() method");
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting user by ID: {}", id, e);
            throw e;
        }
    }
}
