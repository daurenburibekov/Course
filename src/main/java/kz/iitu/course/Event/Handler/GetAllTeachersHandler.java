package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.GetAllTeachersEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class GetAllTeachersHandler implements ApplicationListener<GetAllTeachersEvent> {
    @Override
    public void onApplicationEvent(GetAllTeachersEvent getAllTeachersEvent) {
        System.out.println("GetAllTeachersHandler.onApplicationEvent");
    }
}
