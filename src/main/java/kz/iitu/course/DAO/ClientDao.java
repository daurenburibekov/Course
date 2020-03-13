package kz.iitu.course.DAO;

import kz.iitu.course.Client.*;
import kz.iitu.course.Event.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class ClientDao implements ApplicationEventPublisherAware {

    @Autowired
    private ConnectionDao connectionDao;
    private ApplicationEventPublisher eventPublisher;

    public void createAdultClient(String name) throws SQLException {
        if(!connectionDao.getClientByName(name)) {
            Adult adult = new Adult(name);
            connectionDao.addClient(adult);
            this.eventPublisher.publishEvent(new ClientCreateEvent(this, adult));
            adult = null;
        } else {
            System.out.println("Уже существует такой клиент");
        }
    }
    public void createChildClient(String name) throws SQLException {
        if( !connectionDao.getClientByName(name)) {
            Child child = new Child(name);
            connectionDao.addClient(child);
            this.eventPublisher.publishEvent(new ClientCreateEvent(this, child));
            child = null;
        } else {
            System.out.println("Уже существует такой клиент");
        }
    }
    public void createStudentClient(String name) throws SQLException {
        if( !connectionDao.getClientByName(name)) {
            Student student = new Student(name);
            connectionDao.addClient(student);
            this.eventPublisher.publishEvent(new ClientCreateEvent(this, student));
            student = null;
        } else {
            System.out.println("Уже существует такой клиент");
        }
    }
    public void updateClient(int id, Client client) throws SQLException {
        if (connectionDao.getClientById(id) != 0) {
            connectionDao.updateClient(id, client);
            this.eventPublisher.publishEvent(new ClientModifyEvent(this, client));
        }
    }
    public void deleteClient(String name) throws SQLException {
        if (connectionDao.getClientByName(name)) {
            connectionDao.deleteClientByName(name);
            this.eventPublisher.publishEvent(new ClientDeleteEvent(this, name));
        }
    }

    public void createTeacher(String name) throws SQLException {
        if( connectionDao.getTeacherByName(name) == null) {
            Teacher teacher = new Teacher(name);
            connectionDao.createTeacher(new Teacher(name));
            this.eventPublisher.publishEvent(new ClientCreateEvent(this, teacher));
            teacher = null;
        } else {
            System.out.println("Уже существует такой преподаватель");
        }
    }
    public void deleteTeacher(String name) throws SQLException {
        if (connectionDao.getTeacherByName(name) != null) {
            connectionDao.deleteTeacher(name);
            this.eventPublisher.publishEvent(new ClientDeleteEvent(this, name));
        }
    }

    public void getAllClients() throws SQLException {
        connectionDao.getAllClients();
        this.eventPublisher.publishEvent(new GetAllClientsEvent(this));
    }
    public void getAllTeachers() throws SQLException {
        connectionDao.getAllTeachers();
        this.eventPublisher.publishEvent(new GetAllTeachersEvent(this));

    }
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
