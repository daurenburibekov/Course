package kz.iitu.course.Client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student implements Client {
    private String name;
    private double discount = 70;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return "Student";
    }
}
