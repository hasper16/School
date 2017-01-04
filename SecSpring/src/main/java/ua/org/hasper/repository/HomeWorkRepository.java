package ua.org.hasper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.HomeWork;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.StudentsGroup;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
public interface HomeWorkRepository extends JpaRepository<HomeWork,Long> {
    @Query("SELECT u,hs FROM HomeWork u join u.homeWorkStudentStatuses hs where  hs.student= :student and u.date>= :sdt and u.date<= :edt")
    List<HomeWork> findByStudentDate (@Param("student") Student student,
                                  @Param("sdt")Calendar sdt,
                                  @Param("edt")Calendar edt);


/*    @Query("SELECT u FROM HomeWork u where u.studentsGroup = :studentsGroup and u.subject = :subject and u.status = :homeWorkStatus and u.date>= :sdt and u.date<= :edt")
    List<HomeWork> findByGroupSubjectStatusDate (@Param("studentsGroup") StudentsGroup studentsGroup,
                                    @Param("subject") Subject subject,
                                    @Param("status") HomeWorkStatus homeWorkStatus,
                                    @Param("sdt")Calendar sdt,
                                    @Param("edt")Calendar edt);

    @Query("SELECT u FROM HomeWork u where u.studentsGroup = :studentsGroup and u.subject = :subject and u.date>= :sdt and u.date<= :edt")
    List<HomeWork> findByGroupSubjectDate (@Param("studentsGroup") StudentsGroup studentsGroup,
                                    @Param("subject") Subject subject,
                                    @Param("sdt")Calendar sdt,
                                    @Param("edt")Calendar edt);

    @Query("SELECT u FROM HomeWork u where u.studentsGroup = :studentsGroup and u.subject = :subject and u.status = :homeWorkStatus and u.date>= :sdt and u.date<= :edt")
    List<HomeWork> findByGroupStatusDate (@Param("studentsGroup") StudentsGroup studentsGroup,
                                          @Param("status") HomeWorkStatus homeWorkStatus,
                                          @Param("sdt")Calendar sdt,
                                          @Param("edt")Calendar edt);*/
}
