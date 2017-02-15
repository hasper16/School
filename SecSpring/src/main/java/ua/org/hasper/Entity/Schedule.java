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

    @ManyToOne
    @JoinColumn(name = "subj_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teach_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER)
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
        setSubject(subject);
        setTeacher(teacher);
        this.studentsGroup = studentsGroup;
        this.weekDayName = weekDayName;
        scheduleTimes.addSchedule(this);

    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        subject.getSchedules().add(this);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.getSchedules().add(this);
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(StudentsGroup studentsGroup) {
        this.studentsGroup = studentsGroup;
        studentsGroup.addSchedule(this);
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

}
