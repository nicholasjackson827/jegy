package com.ngjackson.jegy.controller;

import com.ngjackson.jegy.model.ticket.Ticket;
import com.ngjackson.jegy.model.user.User;
import com.ngjackson.jegy.repository.TicketRepository;
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
public class TicketController {

  private final Logger log = LoggerFactory.getLogger(TicketController.class);
  private TicketRepository ticketRepository;
  private UserRepository userRepository;

  @GetMapping("/tickets")
  Collection<Ticket> getTickets() {
    return ticketRepository.findAll();
  }

  @GetMapping("/ticket/{id}")
  ResponseEntity<?> getTicket(@PathVariable("id") Long ticketId) {
    Optional<Ticket> ticket = ticketRepository.findById(ticketId);
    return ticket.map(response -> ResponseEntity.ok().body(response))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/ticket")
  ResponseEntity<Ticket> createTicket(@Valid @RequestBody Ticket ticket) throws URISyntaxException {
    populateTicket(ticket);
    Ticket newTicket = ticketRepository.save(ticket);
    return ResponseEntity.created(new URI("/api/ticket/" + newTicket.getId()))
        .body(newTicket);
  }

  @PutMapping("/ticket")
  ResponseEntity<Ticket> updateTicket(@Valid @RequestBody Ticket ticket) {
    populateTicket(ticket);
    System.out.println(ticket);
    Ticket result = ticketRepository.save(ticket);
    return ResponseEntity.ok().body(result);
  }

  @DeleteMapping("/ticket/{id}")
  ResponseEntity<?> deleteTicket(@PathVariable Long id) {
    ticketRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }

  private void populateTicket(Ticket ticket) {
    // If the requester object is not null,
    // AND the ID is not null (meaning the FE sent us one)
    // then get the requester
    if (ticket.getRequester() != null && ticket.getRequester().getId() != null) {
      // Fetch from the DB
      User requester = userRepository.findById(ticket.getRequester().getId()).orElse(null);
      ticket.setRequester(requester);
    }

    // If the assignee object is not null,
    // AND the ID is not null (meaning the FE sent us one)
    // then get the assignee
    if (ticket.getAssignee() != null && ticket.getAssignee().getId() != null) {
      // Fetch from the DB
      User assignee = userRepository.findById(ticket.getAssignee().getId()).orElse(null);
      ticket.setAssignee(assignee);
    }
  }

  public TicketController(TicketRepository ticketRepository, UserRepository userRepository) {
    this.ticketRepository = ticketRepository;
    this.userRepository = userRepository;
  }
}
