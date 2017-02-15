package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.*;
import ua.org.hasper.repository.TeacherRepository;
import ua.org.hasper.service.*;

import java.util.List;
import java.util.Locale;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
@Service
@Repository
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private JurnalService jurnalService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private UserService userService;

    @Override
    public void addOrUpdateTeacher(Teacher teacher) { teacherRepository.save(teacher);

    }
    @Override
    public void addOrUpdateTeachers(List<Teacher> teachers){
        teacherRepository.save(teachers);
    }

    @Override
    public void delTeacher(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher findByLogin(String login){
        return teacherRepository.findByUser(login);
    }

    @Override
    public List<Teacher> getAllTeachers(){
        return teacherRepository.allTeachers();
    }

    @Override
    public Page<Teacher> getAllTeachers(int page, int pageSize){
        return teacherRepository.findAll(new PageRequest(page,pageSize));
    }

    @Override
    public Teacher findById(int id){
        Teacher teacher = teacherRepository.findById(id);
        teacher.getSchedules().iterator();
        teacher.getJurnals().iterator();
        teacher.getHomeWorks().iterator();

        return teacher;
    }

    @Override
    public Integer countTeachers (){
        Long count  = teacherRepository.count();
        Integer res = count.intValue();
        return res;
    }
}
