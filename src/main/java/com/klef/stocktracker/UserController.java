package com.klef.stocktracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
//@CrossOrigin("http://localhost:3000/")
//@CrossOrigin("https://stocktrackerapp.vercel.app/")
@CrossOrigin(origins = {"http://localhost:3000", "https://stocktrackerapp.vercel.app"})
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity user) {
        try {
        	UserEntity newUser = userService.registerUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/signin")
    public ResponseEntity<UserEntity> signinUser(@RequestBody UserEntity user){
        try {
            Optional<UserEntity> authenticatedUser = userService.signinUser(user);
            return new ResponseEntity<>(authenticatedUser.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("id") Long id) {
        Optional<UserEntity> user = userService.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get user by username (useful for login)
    @GetMapping("/username/{username}")
    public ResponseEntity<UserEntity> getUserByUsername(@PathVariable("username") String username) {
        Optional<UserEntity> user = userService.getUserByUsername(username);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update user details (for example, email, password, etc.)
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") Long id, @RequestBody UserEntity userDetails) {
        Optional<UserEntity> updatedUser = userService.updateUser(id, userDetails);
        return updatedUser.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) 
                       : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
