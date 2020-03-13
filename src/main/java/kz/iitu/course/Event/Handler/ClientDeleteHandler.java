package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.ClientDeleteEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ClientDeleteHandler implements ApplicationListener<ClientDeleteEvent> {
    @Override
    public void onApplicationEvent(ClientDeleteEvent clientDeleteEvent) {
        System.out.println("ClientDeleteHandler.onApplicationEvent");
        if(clientDeleteEvent.getId() == 0){
            System.out.println("Delete teacher by name: " + clientDeleteEvent.getName());
        } else {
            System.out.println("Delete client by id: " + clientDeleteEvent.getId());
        }
    }
}
