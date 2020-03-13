package kz.iitu.course.Event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class GetTeacherCoursesEvent extends ApplicationEvent {
    public GetTeacherCoursesEvent(Object source) {
        super(source);
    }
}
