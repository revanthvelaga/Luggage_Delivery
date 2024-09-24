package com.example.luggagedelivery.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import com.example.luggagedelivery.entity.User;
import com.example.luggagedelivery.repository.UserRepository;
import com.example.luggagedelivery.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

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

            // If successful, generate a JWT token
            User user = findByUsername(username).orElseThrow(() -> new Exception("User not found"));
            return jwtUtil.generateToken(user.getUsername());
        } catch (Exception e) {
            throw new Exception("Invalid username or password");
        }
    }

}
