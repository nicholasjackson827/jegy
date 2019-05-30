package com.ngjackson.jegy.repository;

import com.ngjackson.jegy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  User findByFirstName(String firstName);

  User findByLastName(String lastName);
}
