package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.Schedule;
import ua.org.hasper.Entity.ScheduleTimes;
import ua.org.hasper.repository.ScheduleTimesRepository;
import ua.org.hasper.service.ScheduleTimesService;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 02.09.2016.
 */
@Service
public class ScheduleTimesServiceImpl implements ScheduleTimesService {

    @Autowired
    private ScheduleTimesRepository scheduleTimesRepository;

    @Override
    @Transactional
    public void addOrUpdateScheduleTimes(ScheduleTimes scheduleTimes){
        scheduleTimesRepository.save(scheduleTimes);
    }

    @Override
    @Transactional
    public void delScheduleTimes(ScheduleTimes scheduleTimes){
        List<Schedule> schedules = scheduleTimes.getSchedules();
        for (Schedule s:
             schedules) {
            s.setScheduleTimes(null);
        }
        scheduleTimesRepository.delete(scheduleTimes);
    }

    @Override
    @Transactional
    public ScheduleTimes findByLessonNum(int n){

        ScheduleTimes scheduleTimes = scheduleTimesRepository.findByLessonNum(n);
        scheduleTimes.getSchedules().iterator();
        return scheduleTimes;
    }

    @Override
    @Transactional
    public List<ScheduleTimes> getAll(){
        return scheduleTimesRepository.getAll();
    }

    @Override
    @Transactional
    public ScheduleTimes findById(int id){
        return scheduleTimesRepository.findById(id);
    }

    @Override
    @Transactional
    public Page<ScheduleTimes> getAll (int page, int pageSize){
        return scheduleTimesRepository.getAll(new PageRequest(page,pageSize));
    }

    @Override
    @Transactional
    public List<ScheduleTimes> findByDurationAndLessonNumAndSdt (int durationMin,
                                                                     int lessonNum,
                                                                     Calendar sdt){
        return scheduleTimesRepository.findByDurationAndLessonNumAndSdt(durationMin,lessonNum,sdt);
    }

}
