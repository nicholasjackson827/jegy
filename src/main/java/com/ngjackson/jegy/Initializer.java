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

    User newUser = User
            .builder()
            .firstName("Big")
            .lastName("Boy")
            .email("big.boy@gmail.com")
            .build();
    newUser = userRepository.save(newUser);
    System.out.println("Just created new user! " + newUser);

    long numTickets = ticketRepository.count();
    Ticket ticket2 = Ticket
        .builder()
        .summary(String.format("%dth ticket!", ++numTickets))
        .body(String.format("This is my %dth ticket! Woo!", numTickets))
        .requester(newUser)
        .build();

    Ticket ticket3 = Ticket
        .builder()
        .summary(String.format("%dth ticket!", ++numTickets))
        .body(String.format("This is my %dth ticket! Woo!", numTickets))
        .requester(newUser)
        .build();

    ticket2 = ticketRepository.save(ticket2);
    ticketRepository.save(ticket3);

    Ticket previouslySavedTicket = ticketRepository.getOne(ticket2.getId());
    previouslySavedTicket.setSummary("Edited this ticket summary!");

    ticketRepository.findAll().forEach(System.out::println);
  }

}
