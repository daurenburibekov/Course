package kz.iitu.course.Event.Handler.DefaultHandlers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class AppContextStartHandler implements ApplicationListener<ContextStartedEvent> {
    @Override
    public void onApplicationEvent(ContextStartedEvent contextStartedEvent) {
        System.out.println("AppContextStartHandler.onApplicationEvent");
    }
}
