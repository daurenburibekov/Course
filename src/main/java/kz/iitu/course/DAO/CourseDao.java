package kz.iitu.course.DAO;

import kz.iitu.course.Client.Teacher;
import kz.iitu.course.Course.Course;
import kz.iitu.course.Event.*;
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
        if (connectionDao.getTeacherByName(teacherName) != null && !connectionDao.getCourseByName(name)) {
            Teacher teacher = connectionDao.getTeacherByName(teacherName);
            Course course = new Course(name, monthlyPayment, month, teacher);
            connectionDao.addCourse(course);
            this.eventPublisher.publishEvent(new CourseCreateEvent(this, course));
            course = null;
            teacher = null;
        } else {
            System.out.println("Такой курс либо уже существует, либо такого преподавателя не существует");
        }
    }

    public void updateCourse(String name, double monthlyPayment, int month) throws SQLException {
        if (connectionDao.getCourseByName(name)) {
            Course course = new Course(name, monthlyPayment, month);
            connectionDao.updateCourse(course);
            this.eventPublisher.publishEvent(new CourseModifyEvent(this, course));
            course = null;
        }
    }

    public void deleteCourse(String name) throws SQLException {
        if (connectionDao.getCourseByName(name)) {
            connectionDao.deleteCourseByName(name);
            this.eventPublisher.publishEvent(new CourseDeleteEvent(this, name));
        }
    }

    public void addToCourse(String name, String coursename) throws SQLException {
        if (!connectionDao.getCourseMember(name, coursename)) {
            if (connectionDao.getClientByName(name) && connectionDao.getCourseByName(coursename)) {
                connectionDao.addToCourse(name, coursename, connectionDao.getTeacherByCourse(coursename));
                this.eventPublisher.publishEvent(new AddToCourseEvent(this, connectionDao.getCourseObjByName(coursename), name));
            }
        } else {
            System.out.println("Такой студент для курса уже существует");
        }
    }

    public void deleteFromCourse(String name, String coursename) throws SQLException {
        if (connectionDao.getClientByName(name) && connectionDao.getCourseByName(coursename)) {
            connectionDao.deleteFromCourse(name, coursename);
            this.eventPublisher.publishEvent(new DeleteFromCourseEvent(this, connectionDao.getCourseObjByName(coursename), name));
        }
    }

    public void getAllCourses() throws SQLException {
        connectionDao.getAllCourses();
        this.eventPublisher.publishEvent(new GetAllCourseEvent(this));

    }

    public void getCourseMembers(String coursename) throws SQLException {
        if (connectionDao.getCourseByName(coursename)) {
            connectionDao.getCourseMembers(coursename);
            this.eventPublisher.publishEvent(new GetCourseMembersEvent(this));
        }
    }

    public void getMemberCourses(String name) throws SQLException {
        if (connectionDao.getClientByName(name)) {
            connectionDao.getMemberCourses(name);
            this.eventPublisher.publishEvent(new GetMemberCoursesEvent(this));
        }
    }

    public void getTeacherCourses(String name) throws SQLException {
        if (connectionDao.getTeacherByName(name) != null) {
            connectionDao.getTeacherCourse(name);
            this.eventPublisher.publishEvent(new GetTeacherCoursesEvent(this));
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }


}
