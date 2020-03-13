package kz.iitu.course.DAO;

import kz.iitu.course.Client.Teacher;
import kz.iitu.course.Course.Course;
import kz.iitu.course.Event.AddToCourseEvent;
import kz.iitu.course.Event.CourseCreateEvent;
import kz.iitu.course.Event.CourseDeleteEvent;
import kz.iitu.course.Event.CourseModifyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class CourseDao implements ApplicationEventPublisherAware {

    @Autowired
    private ConnectionDao connectionDao;
    private ApplicationEventPublisher eventPublisher;

    public void createCourse(String name, double monthlyPayment, int month, String teacherName) throws SQLException {
        if (connectionDao.getTeacherByName(teacherName) != null && connectionDao.getCourseByName(name)==null) {
            Teacher teacher = connectionDao.getTeacherByName(teacherName);
            Course course = new Course(name, monthlyPayment, month, teacher);
            connectionDao.addCourse(course);
            this.eventPublisher.publishEvent(new CourseCreateEvent(this, course));
            course = null;
            teacher = null;
        } else {
            System.out.println("Такой курс уже существует");
        }
    }

    public void updateCourse(String name, double monthlyPayment, int month) throws SQLException {
        if (connectionDao.getCourseByName(name) != null) {
            Course course = new Course(name, monthlyPayment, month);
            connectionDao.updateCourse(course);
            this.eventPublisher.publishEvent(new CourseModifyEvent(this, course));
            course = null;
        }
    }

    public void deleteCourse(String name) throws SQLException {
        if (connectionDao.getCourseByName(name) != null) {
            connectionDao.deleteCourseByName(name);
            this.eventPublisher.publishEvent(new CourseDeleteEvent(this, name));
        }
    }

    public void addToCourse(String name, String coursename) throws SQLException {
        if(!connectionDao.getCourseMember(name, coursename)) {
            if (connectionDao.getClientByName(name) != 0 && connectionDao.getCourseByName(coursename) != null) {
                connectionDao.addToCourse(name, coursename);
                this.eventPublisher.publishEvent(new AddToCourseEvent(this, connectionDao.getCourseObjByName(coursename), name));
            }
        } else {
            System.out.println("Такой студент для курса уже существует");
        }
    }


    public void deleteFromCourse(String name, String coursename) throws SQLException {
        if (connectionDao.getClientByName(name) != 0 && connectionDao.getCourseByName(coursename) != null) {
            connectionDao.deleteFromCourse(name, coursename);
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }


}
