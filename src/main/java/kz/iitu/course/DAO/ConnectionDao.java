package kz.iitu.course.DAO;

import kz.iitu.course.Client.Client;
import kz.iitu.course.Client.Teacher;
import kz.iitu.course.Course.Course;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;

@Component
@Getter
@Setter
public class ConnectionDao {

    @Value("${connectionDao.dbUrl}")
    private String dbUrl;
    @Value("${connectionDao.dbUsername}")
    private String dbUsername;
    @Value("${connectionDao.dbPassword}")
    private String dbPassword;
    private Connection connection;
    private Statement statement;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @PostConstruct
    public void init() throws SQLException {
        this.setConnection();
    }

    public void setConnection() throws SQLException {
        connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        statement = connection.createStatement();
    }

    @PreDestroy
    public void destroy() throws SQLException {
        this.closeConnections();
    }

    public void closeConnections() throws SQLException {
        rs.close();
        connection.close();
    }

    //Clients
    public void addClient(Client client) throws SQLException {
        String SQL = "INSERT INTO courses.clients(name,type) VALUES(?,?)";
        pstmt = connection.prepareStatement(SQL);
        pstmt.setString(1, client.getName());
        pstmt.setString(2, client.getType());
        pstmt.executeUpdate();
    }
    public void updateClient(int id, Client client) throws SQLException {
        String SQL = "UPDATE courses.clients SET name = ?, type = ? WHERE id = ?";
        pstmt = connection.prepareStatement(SQL);
        pstmt.setString(1, client.getName());
        pstmt.setString(2, client.getType());
        pstmt.setInt(3, id);
        pstmt.executeUpdate();
    }
    public void deleteClientByName(String name) throws SQLException {
        statement.executeUpdate("DELETE FROM courses.clients WHERE name = '" + name + "'");
    }
    public int getClientByName(String name) throws SQLException {
        rs = statement.executeQuery("SELECT id FROM courses.clients WHERE name = '" + name + "'");
        if (rs.next()) {
            return rs.getInt(1);
        }
        System.out.println("Такого клиента нету");
        return 0;
    }
    public int getClientById(int id) throws SQLException {
        rs = statement.executeQuery("SELECT id FROM courses.clients WHERE id = '" + id + "'");
        if (rs.next()) {
            return rs.getInt(1);
        }
        System.out.println("Такого клиента нету");
        return 0;
    }

    //Courses
    public void addCourse(Course course) throws SQLException {
        if(getCourseByName(course.getName())!= null) {
            String SQL = "INSERT INTO courses.courses (coursename, payment, month) VALUES(?, ?, ?)";
            pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, course.getName());
            pstmt.setDouble(2, course.getMonthlyPayment());
            pstmt.setInt(3, course.getMonth());
            pstmt.executeUpdate();
        }
    }
    public void updateCourse(Course course) throws SQLException {
        if(getCourseByName(course.getName())!= null) {
            String SQL = "UPDATE courses.courses SET payment = ?, month = ? WHERE coursename = ?";
            pstmt = connection.prepareStatement(SQL);
            pstmt.setDouble(1, course.getMonthlyPayment());
            pstmt.setInt(2, course.getMonth());
            pstmt.setString(3, course.getName());
        }
        pstmt.executeUpdate();
    }
    public void deleteCourseByName(String name) throws SQLException {
        if(getCourseByName(name)!= null) {
            statement.executeUpdate("DELETE FROM courses.courses WHERE coursename = '" + name + "'");
        }
    }
    public String getCourseByName(String coursename) throws SQLException {
        rs = statement.executeQuery("SELECT coursename FROM courses.courses WHERE coursename = '" + coursename + "'");
        if (rs.next()) {
            return rs.getString(1);
        }
        System.out.println("Нету такого курса");
        return null;
    }
    public Course getCourseObjByName(String coursename) throws SQLException {
        rs = statement.executeQuery("SELECT * FROM courses.courses WHERE coursename = '" + coursename + "'");
        if (rs.next()) {
            return new Course(rs.getString(1),rs.getDouble(2),rs.getInt(3) );
        }
        System.out.println("Нету такого курса");
        return null;
    }

    //Teachers
    public Teacher getTeacherByName(String name) throws SQLException {
        rs = statement.executeQuery("SELECT name FROM courses.teachers WHERE name = '" + name + "'");
        if (rs.next()) {
            return new Teacher(name);
        }
        System.out.println("Такого преподователя нету");
        return null;
    }
    public void createTeacher(Teacher teacher) throws SQLException {
        String SQL = "INSERT INTO courses.teachers(name) VALUES(?)";
        pstmt = connection.prepareStatement(SQL);
        pstmt.setString(1, teacher.getName());
        pstmt.executeUpdate();
    }
    public void deleteTeacher(String teacherName) throws SQLException {
        statement.executeUpdate("DELETE FROM courses.teachers WHERE name = '" + teacherName + "'");
    }

    //Add to course student
    public void addToCourse(String name, String coursename) throws SQLException {
        if (getClientByName(name) != 0 || getCourseByName(coursename) != null) {
            String SQL = "INSERT INTO courses.courseclients(client_id, course_id) VALUES(?, ?)";
            pstmt = connection.prepareStatement(SQL);
            pstmt.setInt(1, getClientByName(name));
            pstmt.setString(2, getCourseByName(coursename));
            pstmt.executeUpdate();
        }
    }
    public void deleteFromCourse(String name, String coursename) throws SQLException {
        statement.executeUpdate("DELETE FROM courses.courseclients WHERE client_id = '" + getClientByName(name) + "' and  course_id = '" + coursename + "'");
    }

    public boolean getCourseMember(String name, String coursename) throws SQLException {
        rs = statement.executeQuery("SELECT * FROM courses.courseclients WHERE client_id = '" + getClientByName(name) + "' and course_id = '" + coursename +"'");
        if (rs.next()) {
            return true;
        }
        System.out.println("Такого преподователя нету");
        return false;
    }
}
