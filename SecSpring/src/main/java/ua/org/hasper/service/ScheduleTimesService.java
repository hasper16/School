package ua.org.hasper.service;

import ua.org.hasper.Entity.ScheduleTimes;

/**
 * Created by Pavel.Eremenko on 02.09.2016.
 */

public interface ScheduleTimesService  {
    void addOrUpdateScheduleTimes(ScheduleTimes scheduleTimes);
    void delScheduleTimes(ScheduleTimes scheduleTimes);
    ScheduleTimes findByLessonNum(int n);
}
