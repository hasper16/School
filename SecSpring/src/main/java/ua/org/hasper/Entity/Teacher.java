package ua.org.hasper.Entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Entity
public class Teacher {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String surname;

    private Calendar birthday;
    private long phone;
    private String email;

    @OneToOne
    @JoinColumn(name = "main_user_id")
    private CustomUser user;

       /* @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
        List<Jurnal>jurnals;
        @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
        List<HomeWork>homeWorks;
        @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
        List<Schedule>schedules;*/


    public Teacher() {
    }

    public Teacher(String name, String surname, Calendar birthday, long phone, String email, CustomUser user) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.user = user;

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

    public String getBirthdayString() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(birthday.getTime());
    }

    public String getBirthdayHtml() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(birthday.getTime());
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
    }

   /* public List<Jurnal> getJurnals() {
         return jurnals;
    }

    public void setJurnals(List<Jurnal> jurnals) {
        this.jurnals = jurnals;
    }

    public List<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(List<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }*/
}
