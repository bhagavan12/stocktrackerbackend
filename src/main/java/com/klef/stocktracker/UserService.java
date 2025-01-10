package com.klef.stocktracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    @Override
    public UserEntity registerUser(UserEntity user) {
        // You can add password hashing here before saving
        return userRepository.save(user);
    }

    // Get a user by ID
    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get a user by username
    @Override
    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public Optional<UserEntity> signinUser(UserEntity user) {
//        Optional<UserEntity> existingUser = userRepository.findByUsername(user.getUsername());
        Optional<UserEntity> existingUser = getUserByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            UserEntity euser = existingUser.get();
            if (euser != null && euser.getPassword().equals(user.getPassword())) {
            	return existingUser; // Return authenticated user details
            } else {
            	return Optional.empty();
            }
        }else {
        	return Optional.empty();
        }
    }
    // Update user details
    @Override
    public Optional<UserEntity> updateUser(Long id, UserEntity userDetails) {
        Optional<UserEntity> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
        	UserEntity user = existingUser.get();
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword()); // Make sure to hash it before saving
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    // Delete a user by ID
    @Override
    public boolean deleteUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
