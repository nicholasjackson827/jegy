package com.ngjackson.jegy.repository;

import com.ngjackson.jegy.model.Ticket;
import com.ngjackson.jegy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

  Ticket findBySummary(String summary);

  Ticket findByRequester(User requester);

  Ticket findByAssignee(User assignee);
}
