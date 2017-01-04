package ua.org.hasper.service;

import ua.org.hasper.Entity.Subject;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
public interface SubjectService {
    void addOrUpdateSubject(Subject subject);
    void delSubject(Subject subject);
    Subject findByName(String subname);
    List<Subject> getAllSubjects();
}
