package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.CourseCreateEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CourseCreateHandler implements ApplicationListener<CourseCreateEvent> {
    @Override
    public void onApplicationEvent(CourseCreateEvent courseCreateEvent) {
        System.out.println("CourseCreateHandler.onApplicationEvent");
        System.out.println("Created course: " + courseCreateEvent.getCourse());
    }
}
