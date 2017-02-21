package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.*;
import ua.org.hasper.repository.SubjectRepository;
import ua.org.hasper.service.*;

import java.util.LinkedList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private JurnalService jurnalService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private HomeWorkStudentStatusService homeWorkStudentStatusService;
    @Autowired
    private MarkStampService markStampService;

    @Override
    @Transactional
    public void addOrUpdateSubject(Subject subject) {
        subjectRepository.save(subject);

    }

    @Override
    @Transactional
    public void delSubject(Subject subject) {
        List<Schedule> schedules = scheduleService.findBySubject(subject);
        List<HomeWork> homeWorks = homeWorkService.findBySubject(subject);
        List<HomeWorkStudentStatus> studentStatuses = new LinkedList<>();
        for (HomeWork h :
                homeWorks) {
            studentStatuses.addAll(h.getHomeWorkStudentStatuses());
        }

        List<Jurnal> jurnals = jurnalService.findBySubject(subject);
        List<MarkStamp> markStamps = new LinkedList<>();
        for (Jurnal j : jurnals) {
            markStamps.addAll(j.getMarkStamps());
        }
        scheduleService.delSchedules(schedules);
        homeWorkStudentStatusService.delHomeWorkStudentStatuses(studentStatuses);
        homeWorkService.delHomeWorks(homeWorks);
        markStampService.delMarkStamps(markStamps);
        jurnalService.delJurnals(jurnals);
        subjectRepository.delete(subject);
    }

    @Override
    @Transactional
    public List<Subject> findByName(String subname) {
        return subjectRepository.findByName(subname);
    }

    @Override
    @Transactional
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Subject> getAllSubjects(int page, int pageSize) {
        return subjectRepository.findAll(new PageRequest(page, pageSize));
    }

    @Override
    @Transactional
    public Subject findById(int id) {
        return subjectRepository.findById(id);
    }

}
