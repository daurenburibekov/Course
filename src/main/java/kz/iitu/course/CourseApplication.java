package kz.iitu.course;

import kz.iitu.course.DAO.ClientDao;
import kz.iitu.course.DAO.ConnectionDao;
import kz.iitu.course.DAO.CourseDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.script.ScriptEngine;
import java.sql.SQLException;

public class CourseApplication {

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        CourseDao courseDao = context.getBean("courseDao", CourseDao.class);
        ClientDao clientDao = context.getBean("clientDao", ClientDao.class);
        clientDao.createTeacher("Dauren");
        courseDao.createCourse("Java", 20000, 3, "Dauren");
        clientDao.createChildClient("Dauren");
        courseDao.addToCourse("Dauren", "Java");
    }

}
