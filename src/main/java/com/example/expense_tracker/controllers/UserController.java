package com.example.expense_tracker.controllers;


import com.example.expense_tracker.entities.User;
import com.example.expense_tracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<User> getUserById(@RequestParam int userId) {
        LOGGER.info("Getting the user by id: " + userId);

        Optional<User> userOptional = userService.getUserById(userId);
        return userOptional.map(user -> ResponseEntity.status(HttpStatus.OK).body(user)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        LOGGER.info("Getting the user by email: " + email);
        Optional<User> userOptional = userService.getUserByEmail(email);
        return userOptional.map(user -> ResponseEntity.status(HttpStatus.OK).body(user)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String email, @RequestParam String password) {
        Optional<User> userOptional = userService.getUserByEmail(email);

        if (userOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        if (!password.equals(userOptional.get().getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        LOGGER.info("Creating new user: " + user);
        User newUser = userService.createUser(
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
