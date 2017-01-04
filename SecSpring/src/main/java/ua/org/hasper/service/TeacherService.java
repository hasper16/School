package ua.org.hasper.service;

import ua.org.hasper.Entity.Teacher;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
public interface TeacherService {
    void addOrUpdateTeacher(Teacher teacher);
    void delTeacher(Teacher teacher);
    Teacher findByLogin(String login);
}
