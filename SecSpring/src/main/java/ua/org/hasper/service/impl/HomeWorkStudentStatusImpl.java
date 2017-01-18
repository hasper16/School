package ua.org.hasper.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.Enums.HomeWorkStatus;
import ua.org.hasper.Entity.HomeWorkStudentStatus;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.Subject;
import ua.org.hasper.repository.HomeWorkStudentStatusRepository;
import ua.org.hasper.service.HomeWorkStudentStatusService;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 05.09.2016.
 */
@Service
public class HomeWorkStudentStatusImpl implements HomeWorkStudentStatusService {

    @Autowired
    HomeWorkStudentStatusRepository homeWorkStudentStatusRepository;

    @Override
    @Transactional
    public List<HomeWorkStudentStatus> findByStudentDate(Student student,
                                                         Calendar sdt,
                                                         Calendar edt) {
        return homeWorkStudentStatusRepository.findByStudentDate(student, sdt, edt);
    }

    @Override
    @Transactional
    public void saveOrUpdateHomeWorkStudentStatus(HomeWorkStudentStatus homeWorkStudentStatus) {
        homeWorkStudentStatusRepository.save(homeWorkStudentStatus);
    }

    @Override
    @Transactional
    public void delHomeWorkStudentStatus(HomeWorkStudentStatus homeWorkStudentStatus) {
        homeWorkStudentStatusRepository.delete(homeWorkStudentStatus);
    }

    @Override
    @Transactional
    public HomeWorkStudentStatus findById(int id) {
        return homeWorkStudentStatusRepository.findById(id);
    }

    @Override
    @Transactional
    public List<HomeWorkStudentStatus> findByStudentSubjectDate(Student student,
                                                                Subject subject,
                                                                Calendar sdt,
                                                                Calendar edt) {

        return homeWorkStudentStatusRepository.findByStudentSubjectDate(student, subject, sdt, edt);

    }

    @Override
    @Transactional
    public List<HomeWorkStudentStatus> findByStudentStatusDate(Student student,
                                                               HomeWorkStatus homeWorkStatus,
                                                               Calendar sdt,
                                                               Calendar edt) {
        return homeWorkStudentStatusRepository.findByStudentStatusDate(student, homeWorkStatus, sdt, edt);
    }

    @Override
    @Transactional
    public List<HomeWorkStudentStatus> findByStudentSubjectStatusDate(Student student,
                                                                      Subject subject,
                                                                      HomeWorkStatus homeWorkStatus,
                                                                      Calendar sdt,
                                                                      Calendar edt) {
        return homeWorkStudentStatusRepository.findByStudentSubjectStatusDate(student, subject, homeWorkStatus, sdt, edt);
    }

    @Override
    @Transactional
    public List<HomeWorkStudentStatus> findByStudent(Student student){
        return homeWorkStudentStatusRepository.findByStudent(student);
    }

    @Override
    @Transactional
    public void deleteByStudent(Student student){

        List<HomeWorkStudentStatus>homeWorkStudentStatuses = homeWorkStudentStatusRepository.findByStudent(student);
        for (HomeWorkStudentStatus h:homeWorkStudentStatuses) {
            homeWorkStudentStatusRepository.delete(h);
        }

    }



}
