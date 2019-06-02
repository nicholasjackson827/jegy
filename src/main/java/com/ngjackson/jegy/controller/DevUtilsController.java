package com.ngjackson.jegy.controller;

import com.github.javafaker.Faker;
import com.ngjackson.jegy.model.Ticket;
import com.ngjackson.jegy.model.User;
import com.ngjackson.jegy.repository.TicketRepository;
import com.ngjackson.jegy.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class DevUtilsController {

  private final Logger log = LoggerFactory.getLogger(DevUtilsController.class);
  private TicketRepository ticketRepository;
  private UserRepository userRepository;

  private static final int DEFAULT_NUM_USERS = 20;
  private static final int DEFAULT_NUM_TICKETS = 30;

  /**
   * Clears a DB table, or the entire DB.
   *
   * (For dev purposes only, obviously.)
   *
   * @param tableName The table name to be deleted, empty if you want to delete all tables
   * @return ResponseEntity
   */
  @PostMapping("/dev/clearDb")
  ResponseEntity<?> clearDb(@RequestParam(required = false, name = "table") String tableName) {

    if (tableName == null) {
      ticketRepository.deleteAll();
      userRepository.deleteAll();
      return ResponseEntity.ok().body("Successfully cleared all data!");
    }

    switch (tableName.toLowerCase()) {
      case "ticket":
        ticketRepository.deleteAll();
        break;
      case "user":
        userRepository.deleteAll();
        break;
      default:
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Table \"" + tableName + "\" not found.");
    }

    return ResponseEntity.ok().body("Successfully cleared table \"" + tableName + "\"!");
  }

  /**
   * Seeds all DB tables with fake data.
   *
   * @return The response entity
   */
  @PostMapping("/dev/seedDb")
  ResponseEntity<?> seedDb() {

    // The heavy lifting is done by the Faker class
    Faker faker = new Faker();
    Random random = new Random();

    // Create users, first
    for (int i = 0; i < DEFAULT_NUM_USERS; i++) {
      User tempUser = User
          .builder()
          .firstName(faker.name().firstName())
          .lastName(faker.name().lastName())
          .email(faker.internet().emailAddress())
          .build();
      userRepository.save(tempUser);
    }

    // Create the tickets
    for (int i = 0; i < DEFAULT_NUM_TICKETS; i++) {
      Ticket tempTicket = Ticket
          .builder()
          .summary(faker.lorem().sentence())
          .body(String.join(".", faker.lorem().sentences(4)))
          .requester(getRandomUser())
          .assignee(random.nextDouble() > 0.5 ? null : getRandomUser())
          .build();
      ticketRepository.save(tempTicket);
    }

    return ResponseEntity.ok().body("Successfully seeded DB!");

  }

  public DevUtilsController(TicketRepository ticketRepository, UserRepository userRepository) {
    this.ticketRepository = ticketRepository;
    this.userRepository = userRepository;
  }

  private User getRandomUser() {
    Random random = new Random();
    List<User> users = userRepository.findAll();
    return users.get(random.nextInt(users.size()));
  }

}
