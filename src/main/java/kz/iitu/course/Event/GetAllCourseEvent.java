package kz.iitu.course.Event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class GetAllCourseEvent extends ApplicationEvent {
    public GetAllCourseEvent(Object source) {
        super(source);
    }
}
