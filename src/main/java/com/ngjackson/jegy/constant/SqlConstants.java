package com.ngjackson.jegy.constant;

public class SqlConstants {

  public static final String TICKET_GET_BY_ID = "select id, summary, body, requester, assignee " +
      "from ticket " +
      "where id = :ticket_id";

  public static final String TICKET_CREATE = "insert into " +
      "insert into ticket (summary, body, requester, assignee) values " +
      "(:summary, :body, :requester, :assignee)";

  public static final String TICKET_UPDATE = "update ticket " +
      "set summary = :summary," +
      "body = :body," +
      "requester = :requester," +
      "assignee = :assignee " +
      "where id = :ticket_id";

  public static final String TICKET_DELETE_BY_ID = "delete from ticket where id = :ticket_id";
}
