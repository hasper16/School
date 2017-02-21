package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        studentRepository.saveAndFlush(newStudent);
    }

    @Override
    @Transactional
    public void addStudents(List<Student> students) {
        studentRepository.save(students);
    }

    @Override
    @Transactional
    public List<Student> getAllStudents() {
        return studentRepository.allStudents();
    }

    @Override
    @Transactional
    public Page<Student> getAllStudents(int page, int pageSize) {
        return studentRepository.findAll(new PageRequest(page, pageSize));
    }

    @Override
    @Transactional
    public Student getStudentById(int id) {
        Student student = studentRepository.findById(id);
        /*student.getHomeWorkStudentStatuses().iterator();
        student.getJurnals().iterator();*/
        return student;
    }

    @Override
    @Transactional
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    @Transactional
    public List<Student> getStudentByGroup(StudentsGroup studentsGroup) {
        return studentRepository.findByGroup(studentsGroup);
    }

    @Override
    @Transactional
    public Integer countStudents() {
        Long count = studentRepository.count();
        Integer res = count.intValue();
        return res;
    }

    @Override
    @Transactional
    public List<Student> top5Students() {
        return studentRepository.top5Students(new PageRequest(0, 5));
    }
}


