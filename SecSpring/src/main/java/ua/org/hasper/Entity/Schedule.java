package ua.org.hasper.Entity;

import ua.org.hasper.Entity.Enums.WeekDayName;

import javax.persistence.*;

/**
 * Created by Pavel.Eremenko on 24.08.2016.
 */
@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "subj_id")
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "teach_id")
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "group_id")
    private StudentsGroup studentsGroup;

    @Enumerated(EnumType.STRING)
    private WeekDayName weekDayName;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private ScheduleTimes scheduleTimes;

    public Schedule() {
    }

    public Schedule(Subject subject, Teacher teacher, StudentsGroup studentsGroup, WeekDayName weekDayName, ScheduleTimes scheduleTimes) {
        this.subject = subject;
        this.teacher = teacher;
        this.studentsGroup = studentsGroup;
        this.weekDayName = weekDayName;
        scheduleTimes.addSchedule(this);

    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(StudentsGroup studentsGroup) {
        this.studentsGroup = studentsGroup;
    }

    public WeekDayName getWeekDayName() {
        return weekDayName;
    }

    public void setWeekDayName(WeekDayName weekDayName) {
        this.weekDayName = weekDayName;
    }

    public ScheduleTimes getScheduleTimes() {
        return scheduleTimes;
    }

    public void setScheduleTimes(ScheduleTimes scheduleTimes) {
        this.scheduleTimes = scheduleTimes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
