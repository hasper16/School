package ua.org.hasper.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

@Service
public class HomeWorkStudentStatusImpl implements HomeWorkStudentStatusService {

    @Autowired
    HomeWorkStudentStatusRepository homeWorkStudentStatusRepository;

    @Override
    @Transactional
    public Page<HomeWorkStudentStatus> findByStudentDate(Student student,
                                                         Calendar sdt,
                                                         Calendar edt, int page, int pageSize) {
        return homeWorkStudentStatusRepository.findByStudentDate(student, sdt, edt, new PageRequest(page, pageSize));
    }

    @Override
    @Transactional
    public void saveOrUpdateHomeWorkStudentStatus(HomeWorkStudentStatus homeWorkStudentStatus) {
        homeWorkStudentStatusRepository.saveAndFlush(homeWorkStudentStatus);
    }

    @Override
    @Transactional
    public void saveOrUpdateHomeWorkStudentStatuses(List<HomeWorkStudentStatus> homeWorkStudentStatuses) {
        homeWorkStudentStatusRepository.save(homeWorkStudentStatuses);
    }

    @Override
    @Transactional
    public void delHomeWorkStudentStatus(HomeWorkStudentStatus homeWorkStudentStatus) {
        homeWorkStudentStatusRepository.delete(homeWorkStudentStatus);
    }

    @Override
    @Transactional
    public void delHomeWorkStudentStatuses(List<HomeWorkStudentStatus> homeWorkStudentStatuses) {
        homeWorkStudentStatusRepository.delete(homeWorkStudentStatuses);
    }

    @Override
    @Transactional
    public HomeWorkStudentStatus findById(int id) {
        return homeWorkStudentStatusRepository.findById(id);
    }

    @Override
    @Transactional
    public Page<HomeWorkStudentStatus> findByStudentSubjectDate(Student student,
                                                                Subject subject,
                                                                Calendar sdt,
                                                                Calendar edt, int page, int pageSize) {

        return homeWorkStudentStatusRepository.findByStudentSubjectDate(student, subject, sdt, edt, new PageRequest(page, pageSize));

    }

    @Override
    @Transactional
    public Page<HomeWorkStudentStatus> findByStudentStatusDate(Student student,
                                                               HomeWorkStatus homeWorkStatus,
                                                               Calendar sdt,
                                                               Calendar edt, int page, int pageSize) {
        return homeWorkStudentStatusRepository.findByStudentStatusDate(student, homeWorkStatus, sdt, edt, new PageRequest(page, pageSize));
    }

    @Override
    @Transactional
    public Page<HomeWorkStudentStatus> findByStudentSubjectStatusDate(Student student,
                                                                      Subject subject,
                                                                      HomeWorkStatus homeWorkStatus,
                                                                      Calendar sdt,
                                                                      Calendar edt, int page, int pageSize) {
        return homeWorkStudentStatusRepository.findByStudentSubjectStatusDate(student, subject, homeWorkStatus, sdt, edt, new PageRequest(page, pageSize));
    }

    @Override
    @Transactional
    public List<HomeWorkStudentStatus> findByStudent(Student student) {
        return homeWorkStudentStatusRepository.findByStudent(student);
    }

    @Override
    @Transactional
    public void deleteByStudent(Student student) {

        List<HomeWorkStudentStatus> homeWorkStudentStatuses = homeWorkStudentStatusRepository.findByStudent(student);
        for (HomeWorkStudentStatus h : homeWorkStudentStatuses) {
            homeWorkStudentStatusRepository.delete(h);
        }

    }


}
