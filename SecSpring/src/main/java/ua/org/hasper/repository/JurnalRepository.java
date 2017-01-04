package ua.org.hasper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.Jurnal;
import ua.org.hasper.Entity.Subject;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
public interface JurnalRepository extends JpaRepository<Jurnal,Long> {
    @Query("SELECT u FROM Jurnal u join u.markStamps m where u.student.user.login = :login and m.createDate between :sdt and :edt")
    List<Jurnal> findByLogin (@Param("login") String login, @Param("sdt") Calendar sdt, @Param("edt") Calendar edt);

    @Query("SELECT u FROM Jurnal u join u.markStamps m where u.student.user.login = :login and m.createDate between :sdt and :edt and u.subject= :subject")
    List<Jurnal> findByLoginSubject (@Param("login") String login, @Param("sdt") Calendar sdt, @Param("edt") Calendar edt, @Param("subject") Subject subject);
}
