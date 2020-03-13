package kz.iitu.course;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("kz.iitu.course")
@PropertySource("application.properties")
public class SpringConfiguration {
}
