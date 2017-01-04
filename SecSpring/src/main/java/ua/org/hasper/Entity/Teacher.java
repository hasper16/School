package ua.org.hasper.Entity;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Pavel.Eremenko on 24.08.2016.
 */

@Entity
public class Teacher {

        @Id
        @GeneratedValue
        private int id;

        private String name;
        private String surname;

        private Calendar birthday;
        private int phone;
        private String email;

        @OneToOne
        @JoinColumn(name = "main_user_id")
        private CustomUser user;

        public Teacher() {
        }

    public Teacher(String name, String surname, Calendar birthday, int phone, String email, CustomUser user) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.user = user;

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


}
