package ua.org.hasper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.*;

import java.util.Calendar;
import java.util.List;

public interface HomeWorkRepository extends JpaRepository<HomeWork, Long> {
    @Query("SELECT u,hs FROM HomeWork u join u.homeWorkStudentStatuses hs where  hs.student= :student and u.date>= :sdt and u.date<= :edt")
    List<HomeWork> findByStudentDate(@Param("student") Student student,
                                     @Param("sdt") Calendar sdt,
                                     @Param("edt") Calendar edt);

    @Query("SELECT u FROM HomeWork u where u.teacher=:teacher")
    List<HomeWork> findByTeacher(@Param("teacher") Teacher teacher);

    @Query("SELECT u FROM HomeWork u join u.homeWorkStudentStatuses h where h.student.studentsGroup=:group")
    List<HomeWork> findByGroup(@Param("group") StudentsGroup group);

    @Query("SELECT u FROM HomeWork u where u.subject=:subject")
    List<HomeWork> findBySubject(@Param("subject") Subject subject);

    @Query("SELECT u FROM HomeWork u where u.id=:id")
    HomeWork findById(@Param("id") int id);

}
