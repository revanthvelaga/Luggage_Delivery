package com.example.luggagedelivery.service;

import com.example.luggagedelivery.entity.CustomUserDetailsService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import com.example.luggagedelivery.entity.User;
import com.example.luggagedelivery.repository.UserRepository;
import com.example.luggagedelivery.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // Create a new user
    public User createUser(User user) {
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // Find user by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Find user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Check if email is already in use
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Login method: Authenticates the user and returns a JWT token if valid
    public String login(String username, String password) throws Exception {
        try {


            // Authenticate the user with provided credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Set the authentication object in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // If successful, generate a JWT token
            // If successful, generate a JWT token
            User userDetails = findByUsername(username)
                    .orElseThrow(() -> new Exception("User not found"));
            return jwtService.generateToken(userDetails.getUsername());
        } catch (Exception e) {
            throw new Exception("Invalid username or password");
        }
    }

    public User unlockUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        user.setLocked(false);
        userRepository.save(user);

        return user;
    }

    //get all users
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}
