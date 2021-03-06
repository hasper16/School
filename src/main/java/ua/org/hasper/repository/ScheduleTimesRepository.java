package ua.org.hasper.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.ScheduleTimes;

import java.util.Calendar;
import java.util.List;

public interface ScheduleTimesRepository extends JpaRepository<ScheduleTimes, Long> {
    @Query("SELECT u FROM ScheduleTimes u where u.lessonNum = :n")
    ScheduleTimes findByLessonNum(@Param("n") Integer n);

    @Query("SELECT u FROM ScheduleTimes u where u.id = :id")
    ScheduleTimes findById(@Param("id") int id);

    @Query("SELECT u FROM ScheduleTimes u")
    List<ScheduleTimes> getAll();

    @Query("SELECT u FROM ScheduleTimes u where u.durationMin=:durationMin and u.lessonNum=:lessonNum and u.sdt=:sdt")
    List<ScheduleTimes> findByDurationAndLessonNumAndSdt(@Param(value = "durationMin") int durationMin,
                                                         @Param(value = "lessonNum") int lessonNum,
                                                         @Param(value = "sdt") Calendar sdt);

    @Query("SELECT u FROM ScheduleTimes u")
    Page<ScheduleTimes> getAll(Pageable pageable);
}
