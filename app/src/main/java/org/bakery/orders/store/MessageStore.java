package org.bakery.orders.store;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.bakery.orders.model.Message;

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
