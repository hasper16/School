package ua.org.hasper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.Teacher;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query("SELECT u FROM Teacher u where u.user.login = :login")
    Teacher findByUser (@Param("login") String login);
}