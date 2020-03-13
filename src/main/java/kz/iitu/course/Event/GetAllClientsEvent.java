package kz.iitu.course.Event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class GetAllClientsEvent extends ApplicationEvent {
    public GetAllClientsEvent(Object source) {
        super(source);
    }
}
