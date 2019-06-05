package com.ngjackson.jegy.controller;

import com.ngjackson.jegy.model.ticket.Ticket;
import com.ngjackson.jegy.model.ticket.form.TicketForm;
import com.ngjackson.jegy.model.user.User;
import com.ngjackson.jegy.repository.TicketRepository;
import com.ngjackson.jegy.repository.UserRepository;
import com.ngjackson.jegy.service.TicketService;
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
  private TicketService ticketService;

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
  ResponseEntity<Ticket> createTicket(@Valid @RequestBody TicketForm form) throws URISyntaxException {
    Ticket ticket = marshaler.marshalToTicket(form);
    Ticket newTicket = ticketRepository.save(ticket);
    return ResponseEntity.created(new URI("/api/ticket/" + newTicket.getId()))
        .body(newTicket);
  }

  @PutMapping("/ticket")
  ResponseEntity<Ticket> updateTicket(@PathVariable("id") Long ticketId, @Valid @RequestBody TicketForm form) {
    Ticket ticket = marshaler.marshalToTicket(form);
    ticket.setId(ticketId);
    Ticket result = ticketRepository.save(ticket);
    return ResponseEntity.ok().body(result);
  }

  @DeleteMapping("/ticket/{id}")
  ResponseEntity<?> deleteTicket(@PathVariable Long id) {
    ticketRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }

  public TicketController(TicketRepository ticketRepository, UserRepository userRepository) {
    this.ticketRepository = ticketRepository;
    this.userRepository = userRepository;
    this.ticketService = new TicketService(ticketRepository, userRepository);
  }
}
