package kz.iitu.course.Course;

import kz.iitu.course.Client.Teacher;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Course {
    private String name;
    private double monthlyPayment;
    private int month;
    private Teacher teacher;

    public Course(String name, double monthlyPayment, int month) {
        this.name = name;
        this.monthlyPayment = monthlyPayment;
        this.month = month;
    }
}
