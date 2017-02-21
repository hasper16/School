package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.*;
import ua.org.hasper.Entity.Enums.UserRole;
import ua.org.hasper.repository.UserRepository;
import ua.org.hasper.service.*;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JurnalService jurnalService;
    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private HomeWorkStudentStatusService homeWorkStudentStatusService;

    @Override
    @Transactional(readOnly = true)
    public CustomUser getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public void addUser(CustomUser customUser) {
        userRepository.save(customUser);
    }

    @Override
    @Transactional
    public List<CustomUser> getUsersByRole(UserRole role) {
        return userRepository.findUsersbyRole(role);
    }

    @Override
    @Transactional
    public Page<CustomUser> getUsersByRole(UserRole role, int page, int pageSize) {
        return userRepository.findUsersbyRole(role, new PageRequest(page, pageSize));
    }

    @Override
    @Transactional
    public void deleteUser(CustomUser customUser) {
        if (customUser.getTeacher() != null) {
            Teacher teacher = customUser.getTeacher();
            List<Jurnal> jurnals = jurnalService.findByTeacher(teacher);
            List<HomeWork> homeWorks = homeWorkService.findByTeacher(teacher);
            List<Schedule> schedules = scheduleService.findByTeacher(teacher);
            jurnalService.delJurnals(jurnals);
            homeWorkService.delHomeWorks(homeWorks);
            scheduleService.delSchedules(schedules);

        } else if (customUser.getStudent() != null) {
            Student student = customUser.getStudent();
            List<Jurnal> jurnals = jurnalService.findByStudent(student);
            List<HomeWorkStudentStatus> studentStatuses = homeWorkStudentStatusService.findByStudent(student);
            jurnalService.delJurnals(jurnals);
            homeWorkStudentStatusService.delHomeWorkStudentStatuses(studentStatuses);
        }
        userRepository.delete(customUser);
    }

    @Override
    @Transactional
    public CustomUser getUserById(int id) {
        return userRepository.findById(id);
    }
}
