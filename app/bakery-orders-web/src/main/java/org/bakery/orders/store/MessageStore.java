package org.bakery.orders.store;

import org.bakery.orders.model.Message;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class MessageStore {

    private List<Message> messages = Collections.synchronizedList(new LinkedList<>());

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
