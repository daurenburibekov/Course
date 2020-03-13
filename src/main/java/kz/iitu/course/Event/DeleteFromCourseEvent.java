package kz.iitu.course.Event;

import kz.iitu.course.Client.Client;
import kz.iitu.course.Course.Course;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class DeleteFromCourseEvent extends ApplicationEvent {
    private Course course;
    private String client;
    public DeleteFromCourseEvent(Object source, Course course, String client) {
        super(source);
        this.course = course;
        this.client = client;
    }
}
