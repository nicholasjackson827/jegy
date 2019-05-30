package com.ngjackson.jegy.util;

import com.ngjackson.jegy.model.Ticket;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class SqlUtils {

  public static SqlParameterSource createSqlParams(Ticket ticket) {
    return new MapSqlParameterSource()
        .addValue("ticket_id", ticket.getId())
        .addValue("summary", ticket.getSummary())
        .addValue("body", ticket.getBody())
        .addValue("requester",
            ticket.getRequester() == null ? null : ticket.getRequester().getId())
        .addValue("assignee",
            ticket.getAssignee() == null ? null : ticket.getAssignee().getId());

  }
}
