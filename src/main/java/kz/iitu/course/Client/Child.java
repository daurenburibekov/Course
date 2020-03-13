package kz.iitu.course.Client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Child implements Client {
    private String name;
    private double discount = 70;

    public Child(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return "Child";
    }
}
