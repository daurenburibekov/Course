package kz.iitu.course.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Teacher implements Client {
    private String name;
    @Override
    public String getType() {
        return "Teacher";
    }
}
