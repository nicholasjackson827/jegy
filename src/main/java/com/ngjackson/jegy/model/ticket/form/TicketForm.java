package com.ngjackson.jegy.model.ticket.form;

import com.ngjackson.jegy.model.form.Form;

public class TicketForm extends Form {

    public TicketForm() {
        super();
    }

    public TicketForm setSummary(String summary) {
        super.setParam("summary", summary);
        return this;
    }

    public TicketForm setBody(String body) {
        super.setParam("body", body);
        return this;
    }

    public TicketForm setRequesterUserId(Long userId) {
        super.setParam("requesterUserId", userId);
        return this;
    }
    public TicketForm setAssigneeUserId(Long userId) {
        super.setParam("assigneeUserId", userId);
        return this;
    }

    public String getSummary() {
        return (String)super.getParam("summary");
    }
    public String getBody() {
        return (String)super.getParam("body");
    }
    public Long getRequesterUserId() {
        return (Long)super.getParam("requesterUserId");
    }
    public Long getAssigneeUserId() {
        return (Long)super.getParam("assigneeUserId");
    }
}
