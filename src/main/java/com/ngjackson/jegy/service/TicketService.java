package com.ngjackson.jegy.service;

import com.ngjackson.jegy.model.ticket.Ticket;
import com.ngjackson.jegy.model.ticket.TicketFormTicketMarshaler;
import com.ngjackson.jegy.model.ticket.form.TicketForm;
import com.ngjackson.jegy.repository.TicketRepository;
import com.ngjackson.jegy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    TicketRepository ticketRepository;
    UserRepository userRepository;
    TicketFormTicketMarshaler marshaler;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        marshaler = new TicketFormTicketMarshaler(userRepository);
    }

    public void assignTicket(Long executantId, Long ticketId, Long assigneeId) {
        // TODO
    }
    public void reassignTicket(Long executantId, Long ticketId, Long newAssigneeId) {
        // TODO
    }
    public void changeRequester(Long executantId, Long ticketId, Long newRequesterId) {
        // TODO
    }
}
