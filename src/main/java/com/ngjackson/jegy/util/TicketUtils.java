package com.ngjackson.jegy.util;

import com.ngjackson.jegy.model.Ticket;
import com.ngjackson.jegy.model.User;
import com.ngjackson.jegy.repository.UserRepository;

public class TicketUtils {

  private static UserRepository userRepository;

  /**
   * Fetch the requester and assignee POJO to finish populating the ticket.
   *
   * @param ticket The partially populated ticket.
   */
  public static void populateTicket(Ticket ticket) {
    if (ticket.getRequesterUserId() != null && ticket.getRequester() == null) {
      // Fetch from the DB
      User requester = userRepository.findById(ticket.getRequesterUserId()).get();
      ticket.setRequester(requester);
    }

    if (ticket.getAssigneeUserId() != null && ticket.getAssignee() == null) {
      // Fetch from the DB
      User assignee = userRepository.findById(ticket.getAssigneeUserId()).get();
      ticket.setAssignee(assignee);
    }
  }

  public TicketUtils(UserRepository userRepository) {
    TicketUtils.userRepository = userRepository;
  }
}
