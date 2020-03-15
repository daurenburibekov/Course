package kz.iitu.course;

import kz.iitu.course.DAO.ClientDao;
import kz.iitu.course.DAO.ConnectionDao;
import kz.iitu.course.DAO.CourseDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.script.ScriptEngine;
import java.sql.SQLException;
import java.util.Scanner;

public class CourseApplication {

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        CourseDao courseDao = context.getBean("courseDao", CourseDao.class);
        ClientDao clientDao = context.getBean("clientDao", ClientDao.class);
        ConnectionDao connectionDao = context.getBean("connectionDao", ConnectionDao.class);
        connectionDao.createDb();
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("1. Показать всех клиентов");
            System.out.println("2. Показать все курсы");
            System.out.println("3. Показать всех преподователей");
            System.out.println("4. Создать клиента");
            System.out.println("5. Создать курс");
            System.out.println("6. Создать преподавателя");
            System.out.println("7. Добавить клиента на курс");
            System.out.println("8. Удалить клиента с курса");
            System.out.println("9. Список групп по курсу");
            System.out.println("10. Список курс по клиенту");
            System.out.println("11. Список курс по преподавателю");
            switch (in.nextInt()){
                case 1:
                    clientDao.getAllClients();
                    break;
                case 2:
                    courseDao.getAllCourses();
                    break;
                case 3:
                    clientDao.getAllTeachers();
                    break;
                case 4:
                    System.out.println("1. Создать обычного клиента");
                    System.out.println("2. Создать клиента типа 'Детский'");
                    System.out.println("3. Создать клиента типа 'Студент'");
                    switch (in.nextInt()) {
                        case 1:
                            System.out.println("Пожалуйста напишите имя клиента:");
                            clientDao.createAdultClient(in.next());
                            break;
                        case 2:
                            System.out.println("Пожалуйста напишите имя клиента:");
                            clientDao.createChildClient(in.next());
                            break;
                        case 3:
                            System.out.println("Пожалуйста напишите имя клиента:");
                            clientDao.createStudentClient(in.next());
                            break;
                    }
                    break;
                case 5:
                    System.out.println("Пожалуйста напишите название курса:");
                    System.out.println("Ежемесячная плата:");
                    System.out.println("Срок (в месяцах):");
                    System.out.println("Имя преподователя:");
                    courseDao.createCourse(in.next(), in.nextDouble(), in.nextInt(), in.next());
                    break;
                case 6:
                    System.out.println("Имя преподователя:");
                    clientDao.createTeacher(in.next());
                    break;
                case 7:
                    System.out.println("Имя клиента:");
                    System.out.println("Название курса:");
                    courseDao.addToCourse(in.next(), in.next());
                    break;
                case 8:
                    System.out.println("Имя клиента:");
                    System.out.println("Название курса:");
                    courseDao.deleteFromCourse(in.next(), in.next());
                    break;
                case 9:
                    System.out.println("Название курса:");
                    courseDao.getCourseMembers(in.next());
                    break;
                case 10:
                    System.out.println("Имя студента:");
                    courseDao.getMemberCourses(in.next());
                    break;
                case 11:
                    System.out.println("Имя преподавателя:");
                    courseDao.getTeacherCourses(in.next());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + in.nextInt());
            }
        }
    }
}
