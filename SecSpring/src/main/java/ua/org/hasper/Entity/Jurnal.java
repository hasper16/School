package ua.org.hasper.Entity;

import javafx.collections.transformation.SortedList;
import ua.org.hasper.Entity.Enums.Mark;
import ua.org.hasper.Entity.Enums.MarkType;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
@Entity
public class Jurnal {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne()
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "group_id")
    private StudentsGroup studentsGroup;

    @OneToOne
    @JoinColumn(name = "teach_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "jurnal",cascade = CascadeType.ALL)
    private List<MarkStamp> markStamps = new LinkedList<>();

    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    public Jurnal() {
    }

    public Jurnal(Subject subject, StudentsGroup studentsGroup, Teacher teacher, MarkStamp markStamp, Student student) {
        this.subject = subject;
        this.studentsGroup = studentsGroup;
        this.teacher = teacher;
        markStamp.setJurnal(this);
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
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

    public List<MarkStamp> getMarkStamps() {
        return markStamps;
    }

    public void addMarkStamp(MarkStamp markStamp) {

        markStamps.add(markStamp);
    }
    public void delMarkStamp(MarkStamp markStamp) {
        markStamps.remove(markStamp);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

