package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.GetAllCourseEvent;
import kz.iitu.course.Event.GetMemberCoursesEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class GetMemberCoursesHandler implements ApplicationListener<GetMemberCoursesEvent> {
    @Override
    public void onApplicationEvent(GetMemberCoursesEvent getMemberCoursesEvent) {
        System.out.println("GetMemberCoursesHandler.onApplicationEvent");
    }
}
