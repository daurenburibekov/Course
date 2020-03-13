package kz.iitu.course.Event.Handler.DefaultHandlers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class AppContextClosedHandler implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        System.out.println("AppContextClosedHandler.onApplicationEvent");
    }
}
