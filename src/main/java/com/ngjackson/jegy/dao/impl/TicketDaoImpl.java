package com.ngjackson.jegy.dao.impl;

import com.ngjackson.jegy.constant.SqlConstants;
import com.ngjackson.jegy.dao.TicketDao;
import com.ngjackson.jegy.model.Ticket;
import com.ngjackson.jegy.util.SqlUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class TicketDaoImpl implements TicketDao {

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Override
  public Ticket getById(Long ticketId) {
    SqlParameterSource params = new MapSqlParameterSource().addValue("ticket_id", ticketId);
    return namedParameterJdbcTemplate.query
  }

  @Override
  public Ticket create(Ticket ticket) {
    SqlParameterSource params = SqlUtils.createSqlParams(ticket);
    namedParameterJdbcTemplate.update(SqlConstants.TICKET_CREATE, params);
  }

  @Override
  public Ticket update(Ticket ticket) {
    SqlParameterSource params = SqlUtils.createSqlParams(ticket);
    namedParameterJdbcTemplate.update(SqlConstants.TICKET_UPDATE, params);
  }

  @Override
  public void deleteById(Long ticketId) {
    SqlParameterSource params = new MapSqlParameterSource().addValue("ticket_id", ticketId);
    namedParameterJdbcTemplate.update(SqlConstants.TICKET_DELETE_BY_ID, params);
  }

  public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
    return namedParameterJdbcTemplate;
  }

  public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }
}
