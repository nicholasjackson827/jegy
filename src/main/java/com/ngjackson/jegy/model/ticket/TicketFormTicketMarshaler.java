package com.ngjackson.jegy.model.ticket;

import com.ngjackson.jegy.model.ticket.form.TicketForm;
import com.ngjackson.jegy.repository.UserRepository;

public class TicketFormTicketMarshaler {

    UserRepository userRepository;
    public TicketFormTicketMarshaler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Ticket marshalToTicket(TicketForm form) {
        Ticket ticket = new Ticket();
        ticket.setSummary(form.getSummary());
        ticket.setBody(form.getBody());
        ticket.setRequester(userRepository.getOne(form.getRequesterUserId()));
        ticket.setAssignee(userRepository.getOne(form.getAssigneeUserId()));
        return ticket;
    }

    public TicketForm marshalToTicketForm(Ticket ticket) {
        TicketForm form = new TicketForm();
        form.setSummary(ticket.getSummary());
        form.setBody(ticket.getBody());
        form.setRequesterUserId(ticket.getRequester().getId());
        form.setAssigneeUserId(ticket.getAssignee().getId());
        return form;
    }
}
