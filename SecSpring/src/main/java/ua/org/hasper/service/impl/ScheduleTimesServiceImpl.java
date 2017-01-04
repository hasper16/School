package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.ScheduleTimes;
import ua.org.hasper.repository.ScheduleTimesRepository;
import ua.org.hasper.service.ScheduleTimesService;

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
        scheduleTimesRepository.delete(scheduleTimes);
    }

    @Override
    @Transactional
    public ScheduleTimes findByLessonNum(int n){

        ScheduleTimes scheduleTimes = scheduleTimesRepository.findByLessonNum(n);
        scheduleTimes.getSchedules().iterator();
        return scheduleTimes;
    }

}
