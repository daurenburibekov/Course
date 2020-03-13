package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.AddToCourseEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AddToCourseHandler implements ApplicationListener<AddToCourseEvent> {
    @Override
    public void onApplicationEvent(AddToCourseEvent addToCourseEvent) {
        System.out.println("AddToCourseHandler.onApplicationEvent");
        System.out.println("Client " + addToCourseEvent.getClient() + " added to course " + addToCourseEvent.getCourse());
    }
}
