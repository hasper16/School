package ua.org.hasper.service;

import ua.org.hasper.Entity.ScheduleTimes;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 02.09.2016.
 */

public interface ScheduleTimesService  {
    void addOrUpdateScheduleTimes(ScheduleTimes scheduleTimes);
    void delScheduleTimes(ScheduleTimes scheduleTimes);
    ScheduleTimes findByLessonNum(int n);
    ScheduleTimes findById(int id);
    List<ScheduleTimes> getAll();
}
