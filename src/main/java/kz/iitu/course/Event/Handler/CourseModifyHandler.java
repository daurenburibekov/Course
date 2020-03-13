package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.CourseModifyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CourseModifyHandler implements ApplicationListener<CourseModifyEvent> {
    @Override
    public void onApplicationEvent(CourseModifyEvent courseModifyEvent) {
        System.out.println("CourseModifyHandler.onApplicationEvent");
        System.out.println("Modify course: " + courseModifyEvent.getCourse());
    }
}
