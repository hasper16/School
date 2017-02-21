package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.*;
import ua.org.hasper.repository.HomeWorkRepository;
import ua.org.hasper.service.HomeWorkService;

import java.util.Calendar;
import java.util.List;

@Service
public class HomeWorkServiceImpl implements HomeWorkService {
    @Autowired
    private HomeWorkRepository homeWorkRepository;


    @Override
    @Transactional
    public void addOrUpdateHomeWork(HomeWork homeWork) {
        homeWorkRepository.saveAndFlush(homeWork);
    }

    @Override
    @Transactional
    public void addOrUpdateHomeWorks(List<HomeWork> homeWorks) {
        homeWorkRepository.save(homeWorks);
    }

    @Override
    @Transactional
    public void delHomeWork(HomeWork homeWork) {

        homeWorkRepository.delete(homeWork);
    }

    @Override
    @Transactional
    public void delHomeWorks(List<HomeWork> homeWorks) {
        homeWorkRepository.delete(homeWorks);
    }

    @Override
    @Transactional
    public List<HomeWork> findByStudentDate(Student student,
                                            Calendar sdt,
                                            Calendar edt) {
        return homeWorkRepository.findByStudentDate(student, sdt, edt);
    }

    @Override
    @Transactional
    public List<HomeWork> findByTeacher(Teacher teacher) {
        return homeWorkRepository.findByTeacher(teacher);
    }

    @Override
    @Transactional
    public List<HomeWork> findByGroup(StudentsGroup studentsGroup) {
        return homeWorkRepository.findByGroup(studentsGroup);
    }

    @Override
    @Transactional
    public List<HomeWork> findBySubject(Subject subject) {
        return homeWorkRepository.findBySubject(subject);

    }

    @Override
    @Transactional
    public List<HomeWork> findAll() {
        return homeWorkRepository.findAll();
    }

    @Override
    @Transactional
    public Page<HomeWork> findAll(int page, int pageSize) {
        return homeWorkRepository.findAll(new PageRequest(page, pageSize));
    }

    @Override
    @Transactional
    public HomeWork findById(int id) {
        return homeWorkRepository.findById(id);
    }

    @Override
    @Transactional
    public Integer countHomeWorks() {
        Long count = homeWorkRepository.count();
        Integer res = count.intValue();
        return res;
    }

}
