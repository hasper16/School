package ua.org.hasper.Entity;

import ua.org.hasper.Entity.Enums.HomeWorkStatus;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class HomeWorkStudentStatus {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    private HomeWorkStatus homeWorkStatus;

    @ManyToOne
    @JoinColumn(name = "homework_id")
    private HomeWork homeWork;


    public HomeWorkStudentStatus() {
    }

    public HomeWorkStudentStatus(Student student, HomeWorkStatus homeWorkStatus, HomeWork homeWork) {
        setStudent(student);
        this.homeWorkStatus = homeWorkStatus;
        this.homeWork = homeWork;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        /*if (!student.getHomeWorkStudentStatuses().contains(this)) {
            student.getHomeWorkStudentStatuses().add(this);
        }*/
    }

    public HomeWorkStatus getStatus() {
        return homeWorkStatus;
    }

    public void setStatus(HomeWorkStatus homeWorkStatus) {
        this.homeWorkStatus = homeWorkStatus;
    }

    public HomeWork getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(HomeWork homeWork) {
        this.homeWork = homeWork;
        homeWork.addHomeWorkStudentStatus(this);
    }

    public List<HomeWorkStatus> getAllStatusWithoutCurent() {
        List<HomeWorkStatus> allStatus = new LinkedList<>();

        for (HomeWorkStatus s : HomeWorkStatus.values()) {
            if (!s.equals(this.getStatus())) {
                allStatus.add(s);
            }
        }
        return allStatus;
    }

    public int getId() {
        return id;
    }
}
