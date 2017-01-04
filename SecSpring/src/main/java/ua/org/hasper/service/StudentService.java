package ua.org.hasper.service;

import ua.org.hasper.Entity.Student;

public interface StudentService {
    Student getStudentByLogin(String login);
    void addStudent(Student student);

}
