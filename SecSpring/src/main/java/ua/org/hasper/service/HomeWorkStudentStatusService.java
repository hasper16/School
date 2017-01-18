package ua.org.hasper.service;

import ua.org.hasper.Entity.Enums.HomeWorkStatus;
import ua.org.hasper.Entity.HomeWork;
import ua.org.hasper.Entity.HomeWorkStudentStatus;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.Subject;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 05.09.2016.
 */
public interface HomeWorkStudentStatusService {
    List<HomeWorkStudentStatus> findByStudentDate (Student student,
                                                   Calendar sdt,
                                                   Calendar edt);
    void saveOrUpdateHomeWorkStudentStatus(HomeWorkStudentStatus homeWorkStudentStatus);
    void delHomeWorkStudentStatus(HomeWorkStudentStatus homeWorkStudentStatus);
    HomeWorkStudentStatus findById (int id);
    List<HomeWorkStudentStatus> findByStudent (Student student);
    void deleteByStudent (Student student);

   List<HomeWorkStudentStatus> findByStudentSubjectDate (Student student,
                                                   Subject subject,
                                                   Calendar sdt,
                                                   Calendar edt);

  List<HomeWorkStudentStatus> findByStudentStatusDate (Student student,
                                                  HomeWorkStatus homeWorkStatus,
                                                   Calendar sdt,
                                                  Calendar edt);

  List<HomeWorkStudentStatus> findByStudentSubjectStatusDate (Student student,
                                                   Subject subject,
                                                   HomeWorkStatus homeWorkStatus,
                                                   Calendar sdt,
                                                   Calendar edt);
}
