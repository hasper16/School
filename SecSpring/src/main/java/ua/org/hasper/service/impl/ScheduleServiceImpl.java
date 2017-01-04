package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.Enums.WeekDayName;
import ua.org.hasper.Entity.Schedule;
import ua.org.hasper.Entity.StudentsGroup;
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
    public void delSchedule(Schedule schedule) {
scheduleRepository.delete(schedule);
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
}
