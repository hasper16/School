package ua.org.hasper.service;

import ua.org.hasper.Entity.Enums.WeekDayName;
import ua.org.hasper.Entity.*;

import java.util.List;

public interface ScheduleService {
    void addOrUpdateSchedule(Schedule schedule);

    void addOrUpdateSchedules(List<Schedule> schedules);

    void delSchedule(Schedule schedule);

    void delSchedules(List<Schedule> schedules);

    List<Schedule> findByWeekDayName(WeekDayName weekDayName, int page, int pageSize);

    List<Schedule> findByWeekDayName(WeekDayName weekDayName);

    List<Schedule> findByWeekDayNameNGroup(WeekDayName weekDayName, StudentsGroup studentsGroup);

    List<Schedule> findByTeacher(Teacher teacher);

    List<Schedule> findByGroup(StudentsGroup studentsGroup);

    List<Schedule> findBySubject(Subject subject);

    List<Schedule> findByScheduleTimes(ScheduleTimes scheduleTimes);

    List<Schedule> findAll();

    Schedule findById(int id);
}
