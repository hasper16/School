package ua.org.hasper.service;

import org.springframework.data.domain.Page;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.StudentsGroup;

import java.util.List;

public interface StudentService {
    Student getStudentByLogin(String login);

    List<Student> getAllStudents();

    Page<Student> getAllStudents(int page, int pageSize);

    List<Student> getStudentByGroup(StudentsGroup studentsGroup);

    void addStudent(Student student);

    void addStudents(List<Student> students);

    Student getStudentById(int id);

    void deleteStudent(Student student);

    Integer countStudents();

    List<Student> top5Students();

}
