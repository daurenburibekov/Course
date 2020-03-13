package kz.iitu.course.Event;

import kz.iitu.course.Client.Client;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ClientCreateEvent extends ApplicationEvent {
    private Client client;
    public ClientCreateEvent(Object source, Client client) {
        super(source);
        this.client = client;
    }
}
