package com.ngjackson.jegy;

import com.ngjackson.jegy.model.Ticket;
import com.ngjackson.jegy.model.User;
import com.ngjackson.jegy.repository.TicketRepository;
import com.ngjackson.jegy.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class Initializer implements CommandLineRunner {

  private final TicketRepository ticketRepository;
  private final UserRepository userRepository;

  public Initializer(TicketRepository ticketRepository, UserRepository userRepository) {
    this.ticketRepository = ticketRepository;
    this.userRepository = userRepository;
  }

  @Transactional
  @Override
  public void run(String... args) throws Exception {

    Ticket ticket2 = Ticket
        .builder()
        .summary("Second ticket!")
        .body("This is my second ticket! Woo!")
        .requester(userRepository.findById(1L).get())
        .build();

    User newUser = User
        .builder()
        .firstName("Bubba")
        .lastName("Gump")
        .email("bubba.gump@gmail.com")
        .build();

    newUser = userRepository.save(newUser);

    System.out.println("Just created new user! " + newUser);

    Ticket ticket3 = Ticket
        .builder()
        .summary("Third ticket!")
        .body("This is my third ticket! Woo!")
        .requester(newUser)
        .build();

    ticketRepository.save(ticket2);
    ticketRepository.save(ticket3);

    Ticket previouslySavedTicket = ticketRepository.getOne(4L);
    previouslySavedTicket.setSummary("Edited this ticket summary!");

    ticketRepository.findAll().forEach(System.out::println);
  }

}
