package kz.iitu.course.Event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CourseDeleteEvent extends ApplicationEvent {
    String name;
    public CourseDeleteEvent(Object source, String name) {
        super(source);
        this.name = name;
    }
}
