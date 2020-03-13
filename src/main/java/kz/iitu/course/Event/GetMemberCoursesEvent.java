package kz.iitu.course.Event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class GetMemberCoursesEvent extends ApplicationEvent {
    public GetMemberCoursesEvent(Object source) {
        super(source);
    }
}
