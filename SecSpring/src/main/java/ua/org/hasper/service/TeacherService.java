package ua.org.hasper.service;

import org.springframework.data.domain.Page;
import ua.org.hasper.Entity.Teacher;

import java.util.List;

public interface TeacherService {
    void addOrUpdateTeacher(Teacher teacher);

    void addOrUpdateTeachers(List<Teacher> teachers);

    void delTeacher(Teacher teacher);

    Teacher findByLogin(String login);

    Teacher findById(int id);

    List<Teacher> getAllTeachers();

    Page<Teacher> getAllTeachers(int page, int pageSize);

    Integer countTeachers();
}
