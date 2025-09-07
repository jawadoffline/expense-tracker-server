package com.example.expense_tracker.services;

import com.example.expense_tracker.entities.User;
import com.example.expense_tracker.respositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());


    @Autowired
    private UserRespository userRespository;

    public Optional<User> getUserById(int userId) {
        LOGGER.info("Getting User with id: " + userId);
        return userRespository.findById(userId);
    }

    public Optional<User> getUserByEmail(String email) {
        LOGGER.info("Getting User with email: " + email);
        return userRespository.findByEmail(email);
    }
}
