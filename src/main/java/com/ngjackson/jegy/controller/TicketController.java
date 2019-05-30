package com.ngjackson.jegy.controller;

import com.ngjackson.jegy.model.Ticket;
import com.ngjackson.jegy.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.metadata.GroupConversionDescriptor;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TicketController {

  private final Logger log = LoggerFactory.getLogger(TicketController.class);
  private TicketRepository ticketRepository;

  @GetMapping("/tickets")
  Collection<Ticket> getTickets() {
    return ticketRepository.findAll();
  }

  @GetMapping("/group/{id}")
  ResponseEntity<?> getTicket(@PathVariable Long ticketId) {
    Optional<Ticket> ticket = ticketRepository.findById(ticketId);
    return ticket.map(response -> ResponseEntity.ok().body(response))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/ticket")
  ResponseEntity<Ticket> createTicket(@Valid @RequestBody Ticket ticket) throws URISyntaxException {
    Ticket newTicket = ticketRepository.save(ticket);
    return ResponseEntity.created(new URI("/api/ticket/" + newTicket.getId()))
        .body(newTicket);
  }

  @PutMapping("/ticket/{id}")
  ResponseEntity<Ticket> updateTicket(@Valid @RequestBody Ticket ticket) {
    Ticket result = ticketRepository.save(ticket);
    return ResponseEntity.ok().body(result);
  }

  @DeleteMapping("/group/{id}")
  ResponseEntity<?> deleteTicket(@PathVariable Long id) {
    ticketRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }

  public TicketController(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }
}
