package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.GetAllClientsEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class GetAllClientsHandler implements ApplicationListener<GetAllClientsEvent> {
    @Override
    public void onApplicationEvent(GetAllClientsEvent getAllClientsEvent) {
        System.out.println("GetAllClientsHandler.onApplicationEvent");
    }
}
