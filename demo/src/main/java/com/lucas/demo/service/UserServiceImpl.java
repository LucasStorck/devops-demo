package com.lucas.demo.service;

import com.lucas.demo.model.User;
import com.lucas.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> getUserById(Long userId) {
    return userRepository.findById(userId);
  }

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public Optional<User> updateUser(Long userId, User userDetails) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      user.setName(userDetails.getName());
      user.setEmail(userDetails.getEmail());
      return Optional.of(userRepository.save(user));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public boolean deleteUser(Long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      userRepository.delete(optionalUser.get());
      return true;
    } else {
      return false;
    }
  }
}
