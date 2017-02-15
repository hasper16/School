package ua.org.hasper.Entity;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
@Entity
public class StudentsGroup {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(mappedBy = "studentsGroup", cascade = CascadeType.ALL)
    private List<Student> students;

    @OneToMany(mappedBy = "studentsGroup", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

/*    @OneToMany(mappedBy = "studentsGroup",  cascade = CascadeType.ALL)
    private List<HomeWork> homeWorks;*/


    public StudentsGroup(String name) {
        this.name = name;
        students = new LinkedList<>();
        schedules = new LinkedList<>();
        /*homeWorks = new LinkedList<>();*/
    }

    public StudentsGroup() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addStudent(Student student) {
        students.add(student);
        student.setStudentsGroup(this);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);

    }
}
   /* public List<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(List<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }
}*/
