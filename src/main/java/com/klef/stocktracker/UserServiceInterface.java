package com.klef.stocktracker;
import java.util.Optional;

public interface UserServiceInterface {

    UserEntity registerUser(UserEntity user);

    Optional<UserEntity> getUserById(Long id);

    Optional<UserEntity> getUserByUsername(String username);

    Optional<UserEntity> updateUser(Long id, UserEntity userDetails);

    boolean deleteUser(Long id);

	Optional<UserEntity> signinUser(UserEntity user);
}

