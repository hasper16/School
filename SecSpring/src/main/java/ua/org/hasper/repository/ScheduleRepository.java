package ua.org.hasper.repository;

import org.hibernate.metamodel.source.annotations.JPADotNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.Enums.WeekDayName;
import ua.org.hasper.Entity.Schedule;
import ua.org.hasper.Entity.StudentsGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    @Query("SELECT u FROM Schedule u where u.weekDayName = :weekDayName")
    List<Schedule> findByWeekDayName (@Param("weekDayName") WeekDayName weekDayName);

    @Query("SELECT u FROM Schedule u where u.weekDayName = :weekDayName and u.studentsGroup=:studentsGroup")
    List<Schedule> findByWeekDayNameNGroup (@Param("weekDayName") WeekDayName weekDayName, @Param("studentsGroup") StudentsGroup studentsGroup);
        }
