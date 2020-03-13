package kz.iitu.course.Event;

import kz.iitu.course.Client.Client;
import kz.iitu.course.Course.Course;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AddToCourseEvent extends ApplicationEvent {
    private Course course;
    private String client;
    public AddToCourseEvent(Object source, Course course, String client) {
        super(source);
        this.course = course;
        this.client = client;
    }
}
