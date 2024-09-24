package com.example.luggagedelivery.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Data
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"username"}),
})
public class User {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    // Email (should be unique)
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    // Encrypted password
    @Column(nullable = false)
    private String password;

    // First and last name
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    // Phone number (optional, length can vary)
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    // Address (can be complex depending on requirements)
    @Column(name = "address", length = 255)
    private String address;

    // Role (e.g., CUSTOMER, ADMIN)
    @Column(nullable = false)
    private String role;

    // Status (ACTIVE, INACTIVE, etc.)
    @Column(nullable = false)
    private String status;

    // Creation and update timestamps
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;





}
