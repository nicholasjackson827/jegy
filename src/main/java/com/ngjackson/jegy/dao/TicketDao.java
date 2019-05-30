package com.ngjackson.jegy.dao;

import com.ngjackson.jegy.model.Ticket;

public interface TicketDao {

  public Ticket create(Ticket ticket);

  public Ticket update(Ticket ticket);

  public Ticket getById(Long ticketId);

  public void deleteById(Long ticketId);
}
