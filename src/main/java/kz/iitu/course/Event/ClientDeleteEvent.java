package kz.iitu.course.Event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ClientDeleteEvent extends ApplicationEvent {
    int id;
    String name;
    public ClientDeleteEvent(Object source, int id) {
        super(source);
        this.id = id;
    }
    public ClientDeleteEvent(Object source, String name) {
        super(source);
        this.name = name;
    }
}
