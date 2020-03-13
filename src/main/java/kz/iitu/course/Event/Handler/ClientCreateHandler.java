package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.ClientCreateEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ClientCreateHandler implements ApplicationListener<ClientCreateEvent> {
    @Override
    public void onApplicationEvent(ClientCreateEvent clientCreateEvent) {
        System.out.println("ClientCreateHandler.onApplicationEvent");
        System.out.println("Created client info: " + clientCreateEvent.getClient());
        System.out.println();
    }

}
