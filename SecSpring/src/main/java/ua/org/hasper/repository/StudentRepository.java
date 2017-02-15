package ua.org.hasper.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.StudentsGroup;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 23.08.2016.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT u FROM Student u where u.user.login = :login")
    Student findByUser(@Param("login") String login);

    @Query(value = "select u from Student u join u.jurnals j join j.markStamps m where m.markType<>'VISIT' group by u order by count(case when m.mark='Accept' then 1 end) desc, count(case when m.mark='m1' then 1 end) desc , count(case when m.mark='m2' then 1 end) desc, count(case when m.mark='m3' then 1 end) DESC, count(case when m.mark='m4' then 1 end) desc, count(case when m.mark='m5' then 1 end) desc, count(case when m.mark='m6' then 1 end) desc, count(case when m.mark='m7' then 1 end) desc, count(case when m.mark='m8' then 1 end) desc, count(case when m.mark='m9' then 1 end) desc, count(case when m.mark='m10' then 1 end) desc, count(case when m.mark='m11' then 1 end) desc, count(case when m.mark='m12' then 1 end) desc")
    List<Student> top5Students(Pageable pageable);


    @Query("SELECT u FROM Student u")
    List<Student> allStudents();

    @Query("SELECT u FROM Student u where u.id = :id")
    Student findById(@Param("id") int id);

    @Query("SELECT u FROM Student u where u.studentsGroup = :group")
    List<Student> findByGroup(@Param("group") StudentsGroup studentsGroup);
}




