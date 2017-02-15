package ua.org.hasper.Entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

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
    private long phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private StudentsGroup studentsGroup;

    @OneToOne
    @JoinColumn(name = "main_user_id")
    private CustomUser user;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<HomeWorkStudentStatus> homeWorkStudentStatuses;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Jurnal> jurnals;


    public Student() {
    }

    public Student(String name, String surname, Calendar birthday, long phone, String email, CustomUser user, StudentsGroup studentsGroup, Photo photo) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        setUser(user);
        studentsGroup.addStudent(this);
        this.photo = photo;
        homeWorkStudentStatuses = new LinkedList<>();
        jurnals = new LinkedList<>();


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

    public String getBirthdayString() {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String s = formatter.format(birthday.getTime());
        return s;

    }

    public String getBirthdayHtml() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(birthday.getTime());
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
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
        user.setStudent(this);
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(StudentsGroup studentsGroup) {
        this.studentsGroup = studentsGroup;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public List<HomeWorkStudentStatus> getHomeWorkStudentStatuses() {
        return homeWorkStudentStatuses;
    }

    public void setHomeWorkStudentStatuses(List<HomeWorkStudentStatus> homeWorkStudentStatuses) {
        this.homeWorkStudentStatuses = homeWorkStudentStatuses;
    }

    public void addHomeWorkStudentStatus(HomeWorkStudentStatus homeWorkStudentStatus) {
        this.homeWorkStudentStatuses.add(homeWorkStudentStatus);
        if (homeWorkStudentStatus.getStudent() != this) {
            homeWorkStudentStatus.setStudent(this);
        }
    }

    public List<Jurnal> getJurnals() {
        return jurnals;
    }

    public void setJurnals(List<Jurnal> jurnals) {
        this.jurnals = jurnals;
    }
}
