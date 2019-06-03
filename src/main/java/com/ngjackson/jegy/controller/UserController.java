package com.ngjackson.jegy.controller;

import com.ngjackson.jegy.model.user.User;
import com.ngjackson.jegy.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

  private final Logger log = LoggerFactory.getLogger(UserController.class);
  private UserRepository userRepository;

  @GetMapping("/users")
  Collection<User> getUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/user/{id}")
  ResponseEntity<?> getUser(@PathVariable("id") Long userId) {
    Optional<User> user = userRepository.findById(userId);
    return user.map(response -> ResponseEntity.ok().body(response))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/user")
  ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
    User newUser = userRepository.save(user);
    return ResponseEntity.created(new URI("/api/user/" + newUser.getId())).body(newUser);
  }

  @PutMapping("/user")
  ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
    User result = userRepository.save(user);
    return ResponseEntity.ok().body(result);
  }

  @DeleteMapping("/user/{id}")
  ResponseEntity<?> deleteUser(@PathVariable Long id) {
    userRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}
