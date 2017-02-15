package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.Enums.WeekDayName;
import ua.org.hasper.Entity.Schedule;
import ua.org.hasper.Entity.StudentsGroup;
import ua.org.hasper.Entity.Subject;
import ua.org.hasper.Entity.Teacher;
import ua.org.hasper.repository.ScheduleRepository;
import ua.org.hasper.service.ScheduleService;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    @Transactional
    public void addOrUpdateSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    @Transactional
    public void addOrUpdateSchedules(List<Schedule> schedules){
        scheduleRepository.save(schedules);
    }

    @Override
    @Transactional
    public void delSchedule(Schedule schedule) {
scheduleRepository.delete(schedule);
    }
    @Override
    @Transactional
    public void delSchedules(List<Schedule> schedules){
        scheduleRepository.delete(schedules);
    }

    @Override
    @Transactional
    public List<Schedule> findByWeekDayName (WeekDayName weekDayName,int page, int pageSize){
        return  scheduleRepository.findByWeekDayName(weekDayName,new PageRequest(page,pageSize));
    }

    @Override
    @Transactional
    public List<Schedule> findByWeekDayName (WeekDayName weekDayName){
        return  scheduleRepository.findByWeekDayName(weekDayName);
    }
    @Override
    @Transactional
    public List<Schedule> findByWeekDayNameNGroup (WeekDayName weekDayName, StudentsGroup studentsGroup){
        return  scheduleRepository.findByWeekDayNameNGroup(weekDayName, studentsGroup);
    }

    @Override
    @Transactional
    public List<Schedule> findByTeacher (Teacher teacher){
        return scheduleRepository.findByTeacher(teacher);
    }

    @Override
    @Transactional
    public List<Schedule> findByGroup (StudentsGroup studentsGroup){
        return scheduleRepository.findByGroup(studentsGroup);
    }

    @Override
    @Transactional
    public List<Schedule> findBySubject (Subject subject){
        return scheduleRepository.findBySubject(subject);
    }

    @Override
    @Transactional
    public Schedule findById(int id){
        return scheduleRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Schedule> findAll (){
        return scheduleRepository.findAll();
    }
}
