package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.HomeWork;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.StudentsGroup;
import ua.org.hasper.repository.HomeWorkRepository;
import ua.org.hasper.service.HomeWorkService;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 31.08.2016.
 */
@Service
public class HomeWorkServiceImpl implements HomeWorkService {
    @Autowired
    HomeWorkRepository homeWorkRepository;

    @Override
    @Transactional
    public void addOrUpdateHomeWork(HomeWork homeWork) {
        homeWorkRepository.save(homeWork);
    }

    @Override
    @Transactional
    public void delHomeWork(HomeWork homeWork) {
        homeWorkRepository.delete(homeWork);
    }

    @Override
    @Transactional
    public List<HomeWork> findByStudentDate(Student student,
                                          Calendar sdt,
                                          Calendar edt) {
        return homeWorkRepository.findByStudentDate(student, sdt, edt);
    }

    /*@Override
    @Transactional
    public List<HomeWork> findByGroupSubjectStatusDate(StudentsGroup studentsGroup,
                                                       Subject subject,
                                                       HomeWorkStatus homeWorkStatus,
                                                       Calendar sdt,
                                                       Calendar edt) {
        return homeWorkRepository.findByGroupSubjectStatusDate(studentsGroup, subject, homeWorkStatus, sdt, edt);
    }

    @Override
    @Transactional
    public List<HomeWork> findByGroupSubjectDate(StudentsGroup studentsGroup,
                                                 Subject subject,
                                                 Calendar sdt,
                                                 Calendar edt) {
        return homeWorkRepository.findByGroupSubjectDate(studentsGroup, subject, sdt, edt);
    }

    @Override
    @Transactional
    public List<HomeWork> findByGroupStatusDate(StudentsGroup studentsGroup,
                                                HomeWorkStatus homeWorkStatus,
                                                Calendar sdt,
                                                Calendar edt) {
        return homeWorkRepository.findByGroupStatusDate(studentsGroup, homeWorkStatus, sdt, edt);
    }*/


}
