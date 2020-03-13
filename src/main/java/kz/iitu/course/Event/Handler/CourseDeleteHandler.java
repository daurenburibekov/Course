package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.CourseDeleteEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CourseDeleteHandler implements ApplicationListener<CourseDeleteEvent> {
    @Override
    public void onApplicationEvent(CourseDeleteEvent courseDeleteEvent) {
        System.out.println("CourseDeleteHandler.onApplicationEvent");
        System.out.println("Delete course by name: " + courseDeleteEvent.getName());
    }
}
