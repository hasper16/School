package ua.org.hasper.Entity;

import ua.org.hasper.Entity.Enums.UserRole;

import javax.persistence.*;

@Entity
public class CustomUser {
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Student student;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Teacher teacher;
    @Id
    @GeneratedValue
    private int id;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public CustomUser(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
        student = null;
        teacher = null;
    }

    public CustomUser() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
