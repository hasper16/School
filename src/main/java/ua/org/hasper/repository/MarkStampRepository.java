package ua.org.hasper.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.Enums.Mark;
import ua.org.hasper.Entity.Enums.MarkType;
import ua.org.hasper.Entity.*;
import ua.org.hasper.Reports.MarkStatistics;

import java.util.Calendar;
import java.util.List;

public interface MarkStampRepository extends JpaRepository<MarkStamp, Long> {
    @Query("select u from MarkStamp u where u.id=:id")
    MarkStamp findById(@Param(value = "id") int id);

    @Query("Select new ua.org.hasper.Reports.MarkStatistics(DATE (u.createDate) as reportDate   , count(CASE WHEN u.mark='m1' THEN u.mark end) as countM1  , count(CASE WHEN u.mark='m2' THEN u.mark end) as countM2  , count(CASE WHEN u.mark='m3' THEN u.mark end) as countM3  , count(CASE WHEN u.mark='m4' THEN u.mark end) as countM4  , count(CASE WHEN u.mark='m5' THEN u.mark end) as countM5  , count(CASE WHEN u.mark='m6' THEN u.mark end) as countM6  , count(CASE WHEN u.mark='m7' THEN u.mark end) as countM7  , count(CASE WHEN u.mark='m8' THEN u.mark end) as countM8  , count(CASE WHEN u.mark='m9' THEN u.mark end) as countM9  , count(CASE WHEN u.mark='m10' THEN u.mark end) as countM10 , count(CASE WHEN u.mark='m11' THEN u.mark end) as countM11 , count(CASE WHEN u.mark='m12' THEN u.mark end) as countM12) from MarkStamp u where u.createDate between :sdt and :edt group by DATE (u.createDate)")
    List<MarkStatistics> Statistic(@Param(value = "sdt") Calendar sdt,
                                   @Param(value = "edt") Calendar edt);

    @Query("select u from MarkStamp u where u.jurnal.student.studentsGroup = :studentsGroup and u.markType = :markType")
    Page<MarkStamp> findByGroupAndMarkType(@Param(value = "studentsGroup") StudentsGroup studentsGroup,
                                           @Param(value = "markType") MarkType markType,
                                           Pageable pageable);

    @Query("select u from MarkStamp u where u.markType = :markType")
    Page<MarkStamp> findByMarkType(@Param(value = "markType") MarkType markType,
                                   Pageable pageable);

    @Query("select u from MarkStamp u where u.markType =:markType and u.mark =:mark and u.jurnal.teacher =:teacher and u.jurnal.student =:student and u.jurnal.subject =:subject")
    List<MarkStamp> findByTypeAndStudentAndTeacherAndSubjectAndMark(@Param(value = "markType") MarkType markType,
                                                                    @Param(value = "student") Student student,
                                                                    @Param(value = "teacher") Teacher teacher,
                                                                    @Param(value = "subject") Subject subject,
                                                                    @Param(value = "mark") Mark mark);
}
