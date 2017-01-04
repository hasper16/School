package ua.org.hasper.Entity;

import ua.org.hasper.Entity.Enums.HomeWorkStatus;
import ua.org.hasper.Entity.HomeWork;
import ua.org.hasper.Entity.Student;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 05.09.2016.
 */
@Entity
public class HomeWorkStudentStatus {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    private HomeWorkStatus homeWorkStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "homework_id")
    private HomeWork homeWork;

    public HomeWorkStudentStatus() {
    }

    public HomeWorkStudentStatus(Student student, HomeWorkStatus homeWorkStatus, HomeWork homeWork) {
        this.student = student;
        this.homeWorkStatus = homeWorkStatus;
        this.homeWork = homeWork;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    public void setId(int id) {
        this.id = id;
    }
}
