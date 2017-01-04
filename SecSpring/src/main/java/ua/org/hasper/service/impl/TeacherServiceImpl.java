package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.Teacher;
import ua.org.hasper.repository.TeacherRepository;
import ua.org.hasper.service.TeacherService;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    @Transactional
    public void addOrUpdateTeacher(Teacher teacher) { teacherRepository.save(teacher);

    }

    @Override
    @Transactional
    public void delTeacher(Teacher teacher) { teacherRepository.delete(teacher);

    }

    @Override
    @Transactional
    public Teacher findByLogin(String login){
        return teacherRepository.findByUser(login);
    }
}
