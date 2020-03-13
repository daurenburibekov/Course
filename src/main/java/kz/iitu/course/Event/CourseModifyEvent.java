package kz.iitu.course.Event;

import kz.iitu.course.Course.Course;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CourseModifyEvent extends ApplicationEvent {
    Course course;
    public CourseModifyEvent(Object source, Course course) {
        super(source);
        this.course = course;
    }
}
