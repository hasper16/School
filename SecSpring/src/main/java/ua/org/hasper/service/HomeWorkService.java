package ua.org.hasper.service;

import org.springframework.data.domain.Page;
import ua.org.hasper.Entity.*;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
public interface HomeWorkService {
    void addOrUpdateHomeWork(HomeWork homeWork);
    void addOrUpdateHomeWorks(List<HomeWork> homeWorks);
    void delHomeWork(HomeWork homeWork);
    void delHomeWorks(List<HomeWork> homeWorks);
    List<HomeWork> findByStudentDate (Student student,
                                    Calendar sdt,
                                    Calendar edt);
    List<HomeWork> findByTeacher(Teacher teacher);
    List<HomeWork> findByGroup (StudentsGroup studentsGroup);
    List<HomeWork> findBySubject (Subject subject);
    List<HomeWork> findAll ();
    Page<HomeWork> findAll (int page, int pageSize);
    HomeWork findById(int id);
    Integer countHomeWorks();
}
