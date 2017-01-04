package ua.org.hasper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.Student;

/**
 * Created by Pavel.Eremenko on 23.08.2016.
 */
public interface StudentRepository extends JpaRepository<Student,Long>{
    @Query("SELECT u FROM Student u where u.user.login = :login")
    Student findByUser (@Param("login") String login);


}




