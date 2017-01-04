package ua.org.hasper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
public interface HomeWorkStudentStatusRepository extends JpaRepository<HomeWorkStudentStatus, Long> {
    @Query("SELECT u FROM HomeWorkStudentStatus u " +
            "where u.student=:student " +
            "and u.homeWork.date>= :sdt " +
            "and u.homeWork.date<= :edt")
    List<HomeWorkStudentStatus> findByStudentDate(@Param("student") Student student,
                                                  @Param("sdt") Calendar sdt,
                                                  @Param("edt") Calendar edt);

    @Query("SELECT u FROM HomeWorkStudentStatus u " +
            "where u.id=:id")
    HomeWorkStudentStatus findById(@Param("id") int id);

    @Query("SELECT u FROM HomeWorkStudentStatus u " +
            "where u.student=:student  " +
            "and u.homeWork.subject=:subject " +
            "and u.homeWork.date>= :sdt " +
            "and u.homeWork.date<= :edt")
    List<HomeWorkStudentStatus> findByStudentSubjectDate(@Param("student") Student student,
                                                         @Param("subject") Subject subject,
                                                         @Param("sdt") Calendar sdt,
                                                         @Param("edt") Calendar edt);

    @Query("SELECT u FROM HomeWorkStudentStatus u " +
            "where u.student=:student  " +
            "and u.homeWorkStatus=:status " +
            "and u.homeWork.date>= :sdt " +
            "and u.homeWork.date<= :edt")
    List<HomeWorkStudentStatus> findByStudentStatusDate(@Param("student") Student student,
                                                        @Param("status") HomeWorkStatus homeWorkStatus,
                                                        @Param("sdt") Calendar sdt,
                                                        @Param("edt") Calendar edt);

    @Query("SELECT u FROM HomeWorkStudentStatus u " +
            "where u.student=:student  " +
            "and u.homeWork.subject=:subject " +
            "and u.homeWorkStatus=:status " +
            "and u.homeWork.date>= :sdt " +
            "and u.homeWork.date<= :edt")
    List<HomeWorkStudentStatus> findByStudentSubjectStatusDate(@Param("student") Student student,
                                                               @Param("subject") Subject subject,
                                                               @Param("status") HomeWorkStatus homeWorkStatus,
                                                               @Param("sdt") Calendar sdt,
                                                               @Param("edt") Calendar edt);
}