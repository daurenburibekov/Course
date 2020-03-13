package kz.iitu.course.Event;

import kz.iitu.course.Course.Course;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CourseCreateEvent extends ApplicationEvent {
    private Course course;
    public CourseCreateEvent(Object source, Course course) {
        super(source);
        this.course = course;
    }
}
