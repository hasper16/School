package ua.org.hasper.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.*;
import ua.org.hasper.Entity.Enums.Mark;
import ua.org.hasper.Entity.Enums.MarkType;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 29.09.2016.
 */
public interface MarkStampService {
    void addOrUpdateMarkStamp(MarkStamp markStamp);
    void addOrUpdateMarkStamps(List<MarkStamp> markStamps);
    void delMarkStamp(MarkStamp markStamp);
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
