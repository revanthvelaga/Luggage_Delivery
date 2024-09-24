package com.example.luggagedelivery.service;

import com.example.luggagedelivery.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {
    // Find a user by username (optional since user might not exist)
    Optional<User> findByUsername(String username);

    // Find a user by email (optional since user might not exist)
    Optional<User> findByEmail(String email);

    // Check if a user exists by email
    boolean existsByEmail(String email);

    String login(String username, String password) throws Exception;
}
