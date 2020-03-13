package kz.iitu.course.Event.Handler.DefaultHandlers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;

public class AppContextStoppedHandler implements ApplicationListener<ContextStoppedEvent> {
    @Override
    public void onApplicationEvent(ContextStoppedEvent contextStoppedEvent) {
        System.out.println("AppContextStoppedHandler.onApplicationEvent");
    }
}
