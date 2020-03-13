package kz.iitu.course.Event;

import kz.iitu.course.Client.Client;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ClientModifyEvent extends ApplicationEvent {
    Client client;
    public ClientModifyEvent(Object source, Client client) {
        super(source);
        this.client = client;
    }
}
