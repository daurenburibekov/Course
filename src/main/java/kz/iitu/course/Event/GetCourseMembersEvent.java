package kz.iitu.course.Event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class GetCourseMembersEvent extends ApplicationEvent {
    public GetCourseMembersEvent(Object source) {
        super(source);
    }
}
