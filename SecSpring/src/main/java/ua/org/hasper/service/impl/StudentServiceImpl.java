package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.StudentsGroup;
import ua.org.hasper.repository.StudentRepository;
import ua.org.hasper.service.StudentService;

import java.util.List;

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

    @Override
    @Transactional
    public List<Student> getAllStudents() {
        return studentRepository.allStudents();
    }

    @Override
    @Transactional
    public Student getStudentById(int id){
        return studentRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteStudent(Student student){
        studentRepository.delete(student);
    }

    @Override
    @Transactional
    public List<Student> getStudentByGroup(StudentsGroup studentsGroup){
        return studentRepository.findByGroup(studentsGroup);
    }
}


