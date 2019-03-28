package cz.ctu.fee.a4m36jee.seminar.web.store;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import cz.ctu.fee.a4m36jee.seminar.web.model.Message;

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
