package ua.org.hasper.service;


import org.springframework.data.domain.Page;
import ua.org.hasper.Entity.Enums.Mark;
import ua.org.hasper.Entity.Enums.MarkType;
import ua.org.hasper.Entity.*;

import java.util.List;

public interface MarkStampService {
    void addOrUpdateMarkStamp(MarkStamp markStamp);

    void addOrUpdateMarkStamps(List<MarkStamp> markStamps);

    void delMarkStamp(MarkStamp markStamp);

    void delMarkStamps(List<MarkStamp> markStamps);

    MarkStamp findById(int id);

    List<MarkStamp> findByAll();

    Page<MarkStamp> findByGroupAndMarkType(StudentsGroup studentsGroup,
                                           MarkType markType,
                                           int page, int pageSize);

    Page<MarkStamp> findByMarkType(MarkType markType,
                                   int page, int pageSize);

    List<MarkStamp> findByTypeAndStudentAndTeacherAndSubjectAndMark(MarkType markType, Student student, Teacher teacher, Subject subject, Mark mark);

    Integer countMarkStamps();

}
