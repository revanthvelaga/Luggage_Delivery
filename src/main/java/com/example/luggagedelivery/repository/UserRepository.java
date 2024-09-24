package com.example.luggagedelivery.repository;

import com.example.luggagedelivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by username (optional since user might not exist)
    Optional<User> findByUsername(String username);

    // Find a user by email (optional since user might not exist)
    Optional<User> findByEmail(String email);

    // Check if a user exists by email
    boolean existsByEmail(String email);
}
