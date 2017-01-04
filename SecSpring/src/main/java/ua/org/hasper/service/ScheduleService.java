package ua.org.hasper.service;

import ua.org.hasper.Entity.Enums.WeekDayName;
import ua.org.hasper.Entity.Schedule;
import ua.org.hasper.Entity.StudentsGroup;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
public interface ScheduleService {
    void addOrUpdateSchedule(Schedule schedule);
    void delSchedule(Schedule schedule);
    List<Schedule> findByWeekDayName (WeekDayName weekDayName);
    List<Schedule> findByWeekDayNameNGroup (WeekDayName weekDayName, StudentsGroup studentsGroup);
}
