package kz.iitu.course.Event.Handler;

import kz.iitu.course.Event.ClientModifyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ClientModifyHandler implements ApplicationListener<ClientModifyEvent> {
    @Override
    public void onApplicationEvent(ClientModifyEvent clientModifyEvent) {
        System.out.println("ClientModifyHandler.onApplicationEvent");
        System.out.println("Modify client: " + clientModifyEvent.getClient());
    }
}
