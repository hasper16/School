package ua.org.hasper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.StudentsGroup;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 23.08.2016.
 */
public interface StudentRepository extends JpaRepository<Student,Long>{
    @Query("SELECT u FROM Student u where u.user.login = :login")
    Student findByUser (@Param("login") String login);

    @Query("SELECT u FROM Student u")
    List<Student> allStudents ();

    @Query("SELECT u FROM Student u where u.id = :id")
    Student findById (@Param("id") int id);

    @Query("SELECT u FROM Student u where u.studentsGroup = :group")
    List<Student> findByGroup (@Param("group")StudentsGroup studentsGroup);




}




