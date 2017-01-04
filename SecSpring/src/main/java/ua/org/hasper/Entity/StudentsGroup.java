package ua.org.hasper.Entity;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
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
    private List<Student> students = new LinkedList<>();

    public StudentsGroup() {
    }

    public StudentsGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transactional
    public void addStudent(Student student) {
        student.setStudentsGroup(this);
        students.add(student);
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }



}

