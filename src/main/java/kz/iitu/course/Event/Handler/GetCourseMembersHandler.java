package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.GetAllCourseEvent;
import kz.iitu.course.Event.GetCourseMembersEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class GetCourseMembersHandler implements ApplicationListener<GetCourseMembersEvent> {
    @Override
    public void onApplicationEvent(GetCourseMembersEvent getCourseMembersEvent) {
        System.out.println("GetCourseMembersHandler.onApplicationEvent");
    }
}
