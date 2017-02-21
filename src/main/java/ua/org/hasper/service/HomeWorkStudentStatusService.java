package ua.org.hasper.service;

import org.springframework.data.domain.Page;
import ua.org.hasper.Entity.Enums.HomeWorkStatus;
import ua.org.hasper.Entity.HomeWorkStudentStatus;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.Subject;

import java.util.Calendar;
import java.util.List;

public interface HomeWorkStudentStatusService {
    Page<HomeWorkStudentStatus> findByStudentDate(Student student,
                                                  Calendar sdt,
                                                  Calendar edt,
                                                  int page, int pageSize);

    void saveOrUpdateHomeWorkStudentStatus(HomeWorkStudentStatus homeWorkStudentStatus);

    void saveOrUpdateHomeWorkStudentStatuses(List<HomeWorkStudentStatus> homeWorkStudentStatuses);

    void delHomeWorkStudentStatus(HomeWorkStudentStatus homeWorkStudentStatus);

    void delHomeWorkStudentStatuses(List<HomeWorkStudentStatus> homeWorkStudentStatuses);

    HomeWorkStudentStatus findById(int id);

    List<HomeWorkStudentStatus> findByStudent(Student student);

    void deleteByStudent(Student student);

    Page<HomeWorkStudentStatus> findByStudentSubjectDate(Student student,
                                                         Subject subject,
                                                         Calendar sdt,
                                                         Calendar edt,
                                                         int page, int pageSize);

    Page<HomeWorkStudentStatus> findByStudentStatusDate(Student student,
                                                        HomeWorkStatus homeWorkStatus,
                                                        Calendar sdt,
                                                        Calendar edt,
                                                        int page, int pageSize);

    Page<HomeWorkStudentStatus> findByStudentSubjectStatusDate(Student student,
                                                               Subject subject,
                                                               HomeWorkStatus homeWorkStatus,
                                                               Calendar sdt,
                                                               Calendar edt, int page, int pageSize);
}
