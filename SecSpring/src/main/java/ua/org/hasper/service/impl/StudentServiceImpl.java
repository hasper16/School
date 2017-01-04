package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.repository.StudentRepository;
import ua.org.hasper.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;


    @Override
    @Transactional(readOnly = true)
    public Student getStudentByLogin(String login) {
        return studentRepository.findByUser(login);
    }

    @Override
    @Transactional
    public void addStudent(Student newStudent) {
        studentRepository.save(newStudent);
    }
}


