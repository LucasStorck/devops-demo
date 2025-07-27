package com.lucas.demo.service;

import com.lucas.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

  List<User> getAllUsers();

  Optional<User> getUserById(Long userId);

  User createUser(User user);

  Optional<User> updateUser(Long userId, User userDetails);

  boolean deleteUser(Long userId);
}
