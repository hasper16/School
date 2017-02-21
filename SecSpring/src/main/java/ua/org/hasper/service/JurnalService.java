package ua.org.hasper.service;

import org.springframework.data.domain.Page;
import ua.org.hasper.Entity.*;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface JurnalService {
    void addOrUpdateJurnal(Jurnal jurnal);

    void addOrUpdateJurnals(List<Jurnal> jurnals);

    void delJurnal(Jurnal jurnal);

    void delJurnals(List<Jurnal> jurnals);

    List<Jurnal> findByLogin(String login, Calendar sdt, Calendar edt);

    Map<Subject, Jurnal> findByLoginForMap(String login, Calendar sdt, Calendar edt);

    List<Jurnal> findByLoginSubject(String login, Calendar sdt, Calendar edt, Subject subject);

    List<Jurnal> findByTeacher(Teacher teacher);

    List<Jurnal> findByStudent(Student student);

    List<Jurnal> findByGroup(StudentsGroup studentsGroup);

    Page<Jurnal> findByGroup(StudentsGroup studentsGroup, int page, int pageSize);

    List<Jurnal> findBySubject(Subject subject);

    List<Jurnal> getAllJurnals();

    Page<Jurnal> getAllJurnals(int page, int pageSize);

}
