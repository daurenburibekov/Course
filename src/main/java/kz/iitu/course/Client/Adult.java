package kz.iitu.course.Client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Adult implements Client {
    private String name;
    private double discount = 100;

    public Adult(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return "Adult";
    }
}
