package ua.org.hasper.service;

import ua.org.hasper.Entity.Enums.WeekDayName;
import ua.org.hasper.Entity.Schedule;
import ua.org.hasper.Entity.StudentsGroup;
import ua.org.hasper.Entity.Subject;
import ua.org.hasper.Entity.Teacher;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
public interface ScheduleService {
    void addOrUpdateSchedule(Schedule schedule);
    void addOrUpdateSchedules(List<Schedule> schedules);
    void delSchedule(Schedule schedule);
    void delSchedules(List<Schedule> schedules);
    List<Schedule> findByWeekDayName (WeekDayName weekDayName, int page, int pageSize);
    List<Schedule> findByWeekDayName (WeekDayName weekDayName);
    List<Schedule> findByWeekDayNameNGroup (WeekDayName weekDayName, StudentsGroup studentsGroup);
    List<Schedule> findByTeacher (Teacher teacher);
    List<Schedule> findByGroup (StudentsGroup studentsGroup);
    List<Schedule> findBySubject (Subject subject);
    List<Schedule> findAll ();
    Schedule findById(int id);
}
