package com.lucas.demo.controller;

import com.lucas.demo.model.User;
import com.lucas.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getAllUser() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) {
    Optional<User> optionalUser = userService.getUserById(userId);
    return optionalUser.map(user -> ResponseEntity.ok().body(user)).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public User createUser(@Validated @RequestBody User user) {
    return userService.createUser(user);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                         @Validated @RequestBody User userDetails) {
    Optional<User> optionalUser = userService.updateUser(userId, userDetails);
    return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable(value = "id") Long userId){
    boolean deleted = userService.deleteUser(userId);
    if(deleted){
      return "USER DELETED";
    } else {
      return "USER DOESN'T EXIT OR COULDN'T BE DELETED";
    }
  }


}
