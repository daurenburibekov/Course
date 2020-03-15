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
    public void createDb() throws SQLException {
        String SQL = "create table if not exists courses.teachers" +
                "(\n" +
                "    name varchar(50) PRIMARY KEY\n" +
                ");\n" +
                "create table if not exists courses.clients" +
                "(" +
                "        id serial PRIMARY KEY," +
                "        name VARCHAR (50) UNIQUE NOT NULL," +
                "        type VARCHAR (50) NOT NULL" +
                ");" +
                "create table if not exists courses.courses" +
                "(\n" +
                "    coursename varchar(50) PRIMARY KEY,\n" +
                "    payment double precision NOT NULL,\n" +
                "    month int NOT NULL,\n" +
                "    teachername VARCHAR (50) NOT NULL,\n" +
                "    CONSTRAINT teachername FOREIGN KEY (teachername)\n" +
                "        REFERENCES courses.teachers (name)\n" +
                ");\n" +
                "\n" +
                "\n" +
                "\n" +
                "create table if not exists courses.courseclients\n" +
                "(\n" +
                "    client_id int NOT NULL,\n" +
                "    course_id varchar(50) NOT NULL,\n" +
                "    teachername varchar(50) NOT NULL,\n" +
                "    CONSTRAINT client_id FOREIGN KEY (client_id)\n" +
                "        REFERENCES courses.clients (id),\n" +
                "    CONSTRAINT course_id FOREIGN KEY (course_id)\n" +
                "        REFERENCES courses.courses (coursename),\n" +
                "    CONSTRAINT teachername FOREIGN KEY (teachername)\n" +
                "        REFERENCES courses.teachers (name)\n" +
                ");";
        pstmt = connection.prepareStatement(SQL);
        pstmt.execute();
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
    public boolean getClientByName(String name) throws SQLException {
        rs = statement.executeQuery("SELECT id FROM courses.clients WHERE name = '" + name + "'");
        if (rs.next()) {
            return true;
        }
        System.out.println("Такого клиента нету");
        return false;
    }
    public int getClientIdByName(String name) throws SQLException {
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
    public void getAllClients() throws SQLException {
        rs = statement.executeQuery("SELECT * FROM courses.clients");
        System.out.printf("%-30.30s %-30.30s %-30.30s%n", "ID", "Name", "Type");
        while (rs.next()) {
            System.out.printf("%-30.30s %-30.30s %-30.30s%n", rs.getInt("id"), rs.getString("name"), rs.getString("type"));
        }
    }

    //Courses
    public void addCourse(Course course) throws SQLException {
        if(!getCourseByName(course.getName())) {
            String SQL = "INSERT INTO courses.courses (coursename, payment, month, teachername) VALUES(?, ?, ?, ?)";
            pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, course.getName());
            pstmt.setDouble(2, course.getMonthlyPayment());
            pstmt.setInt(3, course.getMonth());
            pstmt.setString(4, course.getTeacher().getName());
            pstmt.executeUpdate();
        }
    }
    public void updateCourse(Course course) throws SQLException {
        if(getCourseByName(course.getName())) {
            String SQL = "UPDATE courses.courses SET payment = ?, month = ? WHERE coursename = ?";
            pstmt = connection.prepareStatement(SQL);
            pstmt.setDouble(1, course.getMonthlyPayment());
            pstmt.setInt(2, course.getMonth());
            pstmt.setString(3, course.getName());
        }
        pstmt.executeUpdate();
    }
    public void deleteCourseByName(String name) throws SQLException {
        if(getCourseByName(name)) {
            statement.executeUpdate("DELETE FROM courses.courses WHERE coursename = '" + name + "'");
        }
    }
    public boolean getCourseByName(String coursename) throws SQLException {
        rs = statement.executeQuery("SELECT coursename FROM courses.courses WHERE coursename = '" + coursename + "'");
        if (rs.next()) {
            return true;
        }
        System.out.println("Нету такого курса");
        return false;
    }
    public Course getCourseObjByName(String coursename) throws SQLException {
        rs = statement.executeQuery("SELECT * FROM courses.courses WHERE coursename = '" + coursename + "'");
        if (rs.next()) {
            return new Course(rs.getString(1),rs.getDouble(2),rs.getInt(3),new Teacher(rs.getString(4)));
        }
        System.out.println("Нету такого курса");
        return null;
    }
    public void getAllCourses() throws SQLException {
        rs = statement.executeQuery("SELECT * FROM courses.courses");
        System.out.printf("%-30.30s %-30.30s %-30.30s%n", "Course Name", "Payment of month", "Month");
        while (rs.next()) {
            System.out.printf("%-30.30s %-30.30s %-30.30s%n", rs.getString("coursename"), rs.getString("payment"), rs.getString("month"));
        }
    }
    public void getCourseMembers(String coursename) throws SQLException {
        rs = statement.executeQuery("select name, course_id, teachername from courses.courseclients inner join courses.clients on client_id = id where course_id = '" + coursename + "'");
        System.out.printf("%-30.30s %-30.30s %-30.30s%n", "Имя клиента", "Название курса", "Имя преподавателя");
        while (rs.next()) {
            System.out.printf("%-30.30s %-30.30s %-30.30s%n", rs.getString("name"), rs.getString("course_id"), rs.getString("teachername"));
        }
    }
    public void getMemberCourses(String name) throws SQLException {
        rs = statement.executeQuery("select name, course_id, teachername from courses.courseclients inner join courses.clients on client_id = id where client_id = '" + getClientIdByName(name) + "'");
        System.out.printf("%-30.30s %-30.30s %-30.30s%n", "Имя клиента", "Название курса", "Имя преподавателя");
        while (rs.next()) {
            System.out.printf("%-30.30s %-30.30s %-30.30s%n", rs.getString("name"), rs.getString("course_id"), rs.getString("teachername"));
        }
    }
    public void getTeacherCourse(String name) throws SQLException {
        rs = statement.executeQuery("select name, course_id, teachername from courses.courseclients inner join courses.clients on client_id = id where teachername = '" + name + "'");
        System.out.printf("%-30.30s %-30.30s %-30.30s%n", "Имя клиента", "Название курса", "Имя преподавателя");
        while (rs.next()) {
            System.out.printf("%-30.30s %-30.30s %-30.30s%n", rs.getString("name"), rs.getString("course_id"), rs.getString("teachername"));
        }
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
    public void getAllTeachers() throws SQLException {
        rs = statement.executeQuery("SELECT * FROM courses.teachers");
        System.out.printf("%-30.30s%n", "Name of teacher");
        while (rs.next()) {
            System.out.printf("%-30.30s%n", rs.getString("name"));
        }
    }
    public String getTeacherByCourse(String coursename) throws SQLException {
        rs = statement.executeQuery("SELECT teachername FROM courses.courses WHERE coursename = '" + coursename + "'");
        if (rs.next()) {
            return rs.getString(1);
        }
        System.out.println("Такого преподователя нету");
        return null;
    }

    //Add to course student
    public void addToCourse(String name, String coursename, String teacherName) throws SQLException {
        if (getClientByName(name) || getCourseByName(coursename)) {
            String SQL = "INSERT INTO courses.courseclients(client_id, course_id, teachername) VALUES(?, ?, ?)";
            pstmt = connection.prepareStatement(SQL);
            pstmt.setInt(1, getClientIdByName(name));
            pstmt.setString(2, coursename);
            pstmt.setString(3, teacherName);
            pstmt.executeUpdate();
        }
    }
    public void deleteFromCourse(String name, String coursename) throws SQLException {
        statement.executeUpdate("DELETE FROM courses.courseclients WHERE client_id = '" + getClientIdByName(name) + "' and  course_id = '" + coursename + "'");
    }

    public boolean getCourseMember(String name, String coursename) throws SQLException {
        rs = statement.executeQuery("SELECT * FROM courses.courseclients WHERE client_id = '" + getClientIdByName(name) + "' and course_id = '" + coursename +"'");
        if (rs.next()) {
            return true;
        }
        System.out.println("Таких данных еще нету");
        return false;
    }
}
