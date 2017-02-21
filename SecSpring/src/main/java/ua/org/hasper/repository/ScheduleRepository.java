package ua.org.hasper.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.Enums.WeekDayName;
import ua.org.hasper.Entity.*;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT u FROM Schedule u where u.weekDayName = :weekDayName")
    List<Schedule> findByWeekDayName(@Param("weekDayName") WeekDayName weekDayName, Pageable pageable);

    @Query("SELECT u FROM Schedule u where u.weekDayName = :weekDayName")
    List<Schedule> findByWeekDayName(@Param("weekDayName") WeekDayName weekDayName);


    @Query("SELECT u FROM Schedule u where u.weekDayName = :weekDayName and u.studentsGroup=:studentsGroup")
    List<Schedule> findByWeekDayNameNGroup(@Param("weekDayName") WeekDayName weekDayName, @Param("studentsGroup") StudentsGroup studentsGroup);

    @Query("SELECT u FROM Schedule u where u.teacher=:teacher")
    List<Schedule> findByTeacher(@Param("teacher") Teacher teacher);

    @Query("SELECT u FROM Schedule u where u.studentsGroup=:group")
    List<Schedule> findByGroup(@Param("group") StudentsGroup studentsGroup);

    @Query("SELECT u FROM Schedule u where u.subject=:subject")
    List<Schedule> findBySubject(@Param("subject") Subject subject);

    @Query("SELECT u FROM Schedule u where u.id=:id")
    Schedule findById(@Param("id") int id);

    @Query("SELECT u FROM Schedule u where u.scheduleTimes=:scheduleTimes")
    List<Schedule> findByScheduleTimes(@Param("scheduleTimes") ScheduleTimes scheduleTimes);

}
