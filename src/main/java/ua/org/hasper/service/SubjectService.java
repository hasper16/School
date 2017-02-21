package ua.org.hasper.service;

import org.springframework.data.domain.Page;
import ua.org.hasper.Entity.Subject;

import java.util.List;

public interface SubjectService {
    void addOrUpdateSubject(Subject subject);

    void delSubject(Subject subject);

    List<Subject> findByName(String subname);

    Subject findById(int id);

    List<Subject> getAllSubjects();

    Page<Subject> getAllSubjects(int page, int pageSize);
}
