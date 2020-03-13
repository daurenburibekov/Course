package kz.iitu.course.Event;

import org.springframework.context.ApplicationEvent;

public class GetAllTeachersEvent extends ApplicationEvent {
    public GetAllTeachersEvent(Object source) {
        super(source);
    }
}
