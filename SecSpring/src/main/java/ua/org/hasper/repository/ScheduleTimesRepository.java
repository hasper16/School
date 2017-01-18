package ua.org.hasper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.ScheduleTimes;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 02.09.2016.
 */
public interface ScheduleTimesRepository extends JpaRepository<ScheduleTimes,Long> {
    @Query("SELECT u FROM ScheduleTimes u where u.lessonNum = :n")
    ScheduleTimes findByLessonNum (@Param("n") Integer n);

    @Query("SELECT u FROM ScheduleTimes u where u.id = :id")
    ScheduleTimes findById (@Param("id") int id);

    @Query("SELECT u FROM ScheduleTimes u")
    List<ScheduleTimes> getAll ();
}
