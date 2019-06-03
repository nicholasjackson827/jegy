package com.ngjackson.jegy.model.ticket;

import com.ngjackson.jegy.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketTest {

    @Test
    public void unitTest() {
        // given
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email@email.com");
        String summary = "Test ticket summary";
        String body = "Test ticket body";
        Ticket ticket = Ticket
                .builder()
                .summary(summary)
                .body(body)
                .requester(user)
                .build();

        // test that getters and builder/assignments work
        assert ticket.getSummary().equals(summary) : "Summary is not equal to assigned value! Expected: \"" + summary + "\", Got: \"" + ticket.getSummary() + "\"";
        assert ticket.getBody().equals(body) : "Body is not equal to assigned value! Expected: \"" + body + "\", Got: \"" + ticket.getSummary() + "\"";
        assert ticket.getRequester().equals(user) : "User is not equal to assigned value! Expected: \"" + user + "\", Got: \"" + ticket.getRequester() + "\"";
    }

}
