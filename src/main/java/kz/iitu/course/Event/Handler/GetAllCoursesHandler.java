package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.GetAllCourseEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class GetAllCoursesHandler implements ApplicationListener<GetAllCourseEvent> {
    @Override
    public void onApplicationEvent(GetAllCourseEvent getAllCourseEvent) {
        System.out.println("GetAllCoursesHandler.onApplicationEvent");
    }
}
