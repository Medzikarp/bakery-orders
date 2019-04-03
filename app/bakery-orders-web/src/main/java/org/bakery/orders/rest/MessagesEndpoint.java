package org.bakery.orders.rest;

import org.bakery.orders.model.Message;
import org.bakery.orders.store.MessageStore;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/messages")
public class MessagesEndpoint {

    @Inject
    private MessageStore messages;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getMessages() {
        return messages.getMessages();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addMessage(Message message) {
        messages.addMessage(message);
    }
}
