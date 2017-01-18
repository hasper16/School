package ua.org.hasper.service;

import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.StudentsGroup;

import java.util.List;

public interface StudentService {
    Student getStudentByLogin(String login);
    List<Student> getAllStudents();
    List<Student> getStudentByGroup(StudentsGroup studentsGroup);
    void addStudent(Student student);
    Student getStudentById(int id);
    void deleteStudent(Student student);

}
