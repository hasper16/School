package ua.org.hasper.service;

import org.springframework.data.domain.Page;
import ua.org.hasper.Entity.ScheduleTimes;

import java.util.Calendar;
import java.util.List;

public interface ScheduleTimesService {
    void addOrUpdateScheduleTimes(ScheduleTimes scheduleTimes);

    void delScheduleTimes(ScheduleTimes scheduleTimes);

    ScheduleTimes findByLessonNum(int n);

    ScheduleTimes findById(int id);

    List<ScheduleTimes> getAll();

    Page<ScheduleTimes> getAll(int page, int pageSize);

    List<ScheduleTimes> findByDurationAndLessonNumAndSdt(int durationMin,
                                                         int lessonNum,
                                                         Calendar sdt);

}
