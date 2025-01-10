package com.klef.stocktracker;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Optional: You can add custom queries here if needed
    
    // Find a user by username (for login purposes)
    Optional<UserEntity> findByUsername(String username);
    
    // Find a user by email (for email-based authentication)
    Optional<UserEntity> findByEmail(String email);
    
}
