package ua.org.hasper.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.*;

import java.util.Calendar;
import java.util.List;

public interface JurnalRepository extends JpaRepository<Jurnal, Long> {
    @Query("SELECT u FROM Jurnal u join u.markStamps m where u.student.user.login = :login and m.createDate between :sdt and :edt")
    List<Jurnal> findByLogin(@Param("login") String login, @Param("sdt") Calendar sdt, @Param("edt") Calendar edt);

    @Query("SELECT u FROM Jurnal u join u.markStamps m where u.student.user.login = :login and m.createDate between :sdt and :edt and u.subject= :subject")
    List<Jurnal> findByLoginSubject(@Param("login") String login, @Param("sdt") Calendar sdt, @Param("edt") Calendar edt, @Param("subject") Subject subject);

    @Query("SELECT u FROM Jurnal u where u.teacher=:teacher")
    List<Jurnal> findByTeacher(@Param("teacher") Teacher teacher);

    @Query("SELECT u FROM Jurnal u where u.student=:student")
    List<Jurnal> findByStudent(@Param("student") Student student);

    @Query("SELECT u FROM Jurnal u where u.student.studentsGroup=:group")
    List<Jurnal> findByGroup(@Param("group") StudentsGroup studentsGroup);

    @Query("SELECT u FROM Jurnal u where u.student.studentsGroup=:group")
    Page<Jurnal> findByGroup(@Param("group") StudentsGroup studentsGroup, Pageable pageable);

    @Query("SELECT u FROM Jurnal u where u.subject=:subject")
    List<Jurnal> findBySubject(@Param("subject") Subject subject);


}
