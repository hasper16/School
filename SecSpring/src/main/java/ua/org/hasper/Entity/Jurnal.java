package ua.org.hasper.Entity;

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

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teach_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "jurnal",cascade = CascadeType.ALL)
    private List<MarkStamp> markStamps = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Jurnal() {
    }

    public Jurnal(Subject subject,Teacher teacher, MarkStamp markStamp, Student student) {
        setSubject(subject);
        setTeacher(teacher);
        markStamp.setJurnal(this);
        setStudent(student);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        subject.getJurnals().add(this);
    }

    public StudentsGroup getStudentsGroup() {
        return this.student.getStudentsGroup();
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.getJurnals().add(this);
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
        student.getJurnals().add(this);
    }
}

