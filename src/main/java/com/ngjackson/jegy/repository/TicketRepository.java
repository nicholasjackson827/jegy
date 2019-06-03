package com.ngjackson.jegy.repository;

import com.ngjackson.jegy.model.ticket.Ticket;
import com.ngjackson.jegy.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

  Ticket findBySummary(String summary);

  Ticket findByRequester(User requester);

  Ticket findByAssignee(User assignee);
}
