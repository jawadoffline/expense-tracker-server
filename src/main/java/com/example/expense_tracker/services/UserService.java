package com.example.expense_tracker.services;

import com.example.expense_tracker.entities.User;
import com.example.expense_tracker.respositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRespository userRespository;

    // GET REQUESTS
    public Optional<User> getUserById(int userId) {
        LOGGER.info("Getting User with id: " + userId);
        return userRespository.findById(userId);
    }

    public Optional<User> getUserByEmail(String email) {
        LOGGER.info("Getting User with email: " + email);
        return userRespository.findByEmail(email);
    }

    // POST REQUESTS
    public User createUser(String username, String email, String password) {
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreatedAt(LocalDateTime.now());
        return userRespository.save(user);
    }



}
