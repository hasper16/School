package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.*;
import ua.org.hasper.repository.SubjectRepository;
import ua.org.hasper.service.HomeWorkService;
import ua.org.hasper.service.JurnalService;
import ua.org.hasper.service.ScheduleService;
import ua.org.hasper.service.SubjectService;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
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

    @Override
    @Transactional
    public void addOrUpdateSubject(Subject subject) { subjectRepository.save(subject);

    }

    @Override
    @Transactional
    public void delSubject(Subject subject) {
        List<Schedule>schedules = subject.getSchedules();
        for (Schedule s:
             schedules) {
            s.getStudentsGroup().getSchedules().remove(s);
            s.getTeacher().getSchedules().remove(s);
            scheduleService.delSchedule(s);
        }
        for (HomeWork work:
                subject.getHomeWorks()){
            for (HomeWorkStudentStatus status:
                    work.getHomeWorkStudentStatuses()) {
               status.getStudent().getHomeWorkStudentStatuses().remove(status);
            }
            homeWorkService.delHomeWork(work);
        }
        subjectRepository.delete(subject);
    }

    @Override
    @Transactional
    public List<Subject>  findByName(String subname){
        return subjectRepository.findByName(subname);
    }

    @Override
    @Transactional
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Subject> getAllSubjects(int page, int pageSize){
        return subjectRepository.findAll(new PageRequest(page,pageSize));
    }

    @Override
    @Transactional
    public Subject findById(int id){
        return subjectRepository.findById(id);
    }

}
