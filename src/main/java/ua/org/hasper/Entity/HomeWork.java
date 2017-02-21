package ua.org.hasper.Entity;

import ua.org.hasper.Entity.Enums.HomeWorkStatus;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


@Entity
public class HomeWork {


    @Id
    @GeneratedValue
    private int id;

    private Calendar date;

   /* @ManyToOne
    @JoinColumn
    private StudentsGroup studentsGroup;*/

    @ManyToOne
    @JoinColumn
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subj_id")
    private Subject subject;

    private String description;

    @OneToMany(mappedBy = "homeWork", cascade = CascadeType.ALL)
    private List<HomeWorkStudentStatus> homeWorkStudentStatuses;


    public HomeWork() {
    }

    public HomeWork(Calendar date, StudentsGroup studentsGroup, Teacher teacher, Subject subject, String description) {
        this.date = date;
        setTeacher(teacher);
        setSubject(subject);
        this.description = description;
        this.homeWorkStudentStatuses = new LinkedList<>();
        setStudentsGroup(studentsGroup);
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }


    public StudentsGroup getStudentsGroup() {

        for (HomeWorkStudentStatus s :
                homeWorkStudentStatuses) {
            return s.getStudent().getStudentsGroup();
        }
        return null;

    }

    public void setStudentsGroup(StudentsGroup studentsGroup) {
        for (Student s : studentsGroup.getStudents()) {
            this.homeWorkStudentStatuses.add(new HomeWorkStudentStatus(s, HomeWorkStatus.Assigned, this));
        }


    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        /*teacher.getHomeWorks().add(this);*/
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    /*    subject.getHomeWorks().add(this);*/
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStrDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(this.getDate().getTime());

    }

    public int getId() {
        return id;
    }

    public List<HomeWorkStudentStatus> getHomeWorkStudentStatuses() {
        return homeWorkStudentStatuses;
    }

    public void setHomeWorkStudentStatuses(List<HomeWorkStudentStatus> homeWorkStudentStatuses) {
        this.homeWorkStudentStatuses = homeWorkStudentStatuses;
    }

    public void addHomeWorkStudentStatus(HomeWorkStudentStatus homeWorkStudentStatus) {
        homeWorkStudentStatuses.add(homeWorkStudentStatus);
    }
}
