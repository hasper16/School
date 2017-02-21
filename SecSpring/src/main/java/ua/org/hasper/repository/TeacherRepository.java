package ua.org.hasper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.Teacher;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT u FROM Teacher u where u.user.login = :login")
    Teacher findByUser(@Param("login") String login);

    @Query("SELECT u FROM Teacher u where u.id = :id")
    Teacher findById(@Param("id") int id);

    @Query("SELECT u FROM Teacher u")
    List<Teacher> allTeachers();
}
