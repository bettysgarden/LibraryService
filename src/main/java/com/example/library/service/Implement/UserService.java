package com.example.library.service.Implement;

import com.example.library.entity.User;
import com.example.library.repository.Interface.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements com.example.library.service.Interface.UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
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
