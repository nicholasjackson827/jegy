package com.ngjackson.jegy.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRowMapper implements RowMapper<Ticket> {

  @Override
  public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
    return Ticket
        .builder()
        .id(resultSet.getLong("id"))
        .summary(resultSet.getString("summary"))
        .body(resultSet.getString("body"))
        .requester()
  }

}
