package ua.org.hasper.Entity;

import ua.org.hasper.service.GroupService;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by Pavel.Eremenko on 06.08.2016.
 */
@Entity
public class Student {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String surname;

    private Calendar birthday;
    private int phone;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private StudentsGroup studentsGroup;

    @OneToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name = "main_user_id")
    private CustomUser user;

    public Student(){}
    public Student(String name, String surname, Calendar birthday, int phone, String email, CustomUser user, StudentsGroup studentsGroup) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.user = user;
        studentsGroup.addStudent(this);


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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(StudentsGroup studentsGroup) {
        this.studentsGroup=studentsGroup;
    }

}
