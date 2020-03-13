package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.DeleteFromCourseEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DeleteFromCourseHandler implements ApplicationListener<DeleteFromCourseEvent> {
    @Override
    public void onApplicationEvent(DeleteFromCourseEvent deleteFromCourseEvent) {
        System.out.println("DeleteFromCourseHandler.onApplicationEvent");
        System.out.println("Client " + deleteFromCourseEvent.getClient() + " delete from course " + deleteFromCourseEvent.getCourse());

    }
}
