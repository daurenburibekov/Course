package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.GetTeacherCoursesEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class GetTeacherCoursesHandler implements ApplicationListener<GetTeacherCoursesEvent> {
    @Override
    public void onApplicationEvent(GetTeacherCoursesEvent getTeacherCoursesEvent) {
        System.out.println("GetTeacherCoursesHandler.onApplicationEvent");
    }
}
