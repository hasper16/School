package ua.org.hasper.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import ua.org.hasper.Entity.Enums.HomeWorkStatus;
import ua.org.hasper.service.StudentService;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
@Entity
public class HomeWork {


    @Id
    @GeneratedValue
    private int id;

    private Calendar date;

    @OneToOne
    @JoinColumn
    private StudentsGroup studentsGroup;

    @OneToOne
    @JoinColumn
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "subj_id")
    private Subject subject;

    private String description;

    @OneToMany(mappedBy = "homeWork", cascade = CascadeType.ALL)
    private List<HomeWorkStudentStatus> homeWorkStudentStatuses = new LinkedList<>();


    public HomeWork() {
    }

    public HomeWork(Calendar date, StudentsGroup studentsGroup, Teacher teacher, Subject subject, String description) {
        this.date = date;
        this.studentsGroup = studentsGroup;
        this.teacher = teacher;
        this.subject = subject;
        this.description = description;
        for (Student s:studentsGroup.getStudents()) {
            this.homeWorkStudentStatuses.add(new HomeWorkStudentStatus(s, HomeWorkStatus.Assigned,this));
        }
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(StudentsGroup studentsGroup) {
        this.studentsGroup = studentsGroup;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStrDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(this.getDate().getTime());

    }

    public List<HomeWorkStudentStatus> getHomeWorkStudentStatuses() {
        return homeWorkStudentStatuses;
    }

    public void setHomeWorkStudentStatuses(List<HomeWorkStudentStatus> homeWorkStudentStatuses) {
        this.homeWorkStudentStatuses = homeWorkStudentStatuses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
