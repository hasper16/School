package ua.org.hasper.service;

import ua.org.hasper.Entity.HomeWork;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.StudentsGroup;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
public interface HomeWorkService {
    void addOrUpdateHomeWork(HomeWork homeWork);
    void delHomeWork(HomeWork homeWork);
    List<HomeWork> findByStudentDate (Student student,
                                    Calendar sdt,
                                    Calendar edt);
   /* List<HomeWork> findByGroupSubjectStatusDate (StudentsGroup studentsGroup,
                                                 Subject subject,
                                                 HomeWorkStatus homeWorkStatus,
                                                 Calendar sdt,
                                                 Calendar edt);
    List<HomeWork> findByGroupSubjectDate (StudentsGroup studentsGroup,
                                           Subject subject,
                                           Calendar sdt,
                                           Calendar edt);
    List<HomeWork> findByGroupStatusDate (StudentsGroup studentsGroup,
                                          HomeWorkStatus homeWorkStatus,
                                          Calendar sdt,
                                          Calendar edt);*/
}
