package ua.org.hasper.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Subject implements Comparable<Subject> {
    @Id
    @GeneratedValue
    private int id;
    private String name;

   /* @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Schedule>schedules;
    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<HomeWork>homeWorks;
    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Jurnal>jurnals;*/

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
      /*  schedules=new LinkedList<>();
        homeWorks=new LinkedList<>();
        jurnals=new LinkedList<>();*/

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Subject o) {
        String str1 = this.getName();
        String str2 = o.getName();
        int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
        if (res == 0) {
            res = str1.compareTo(str2);
        }
        return res;
    }

    public int getId() {
        return id;
    }

    /*public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(List<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }

    public List<Jurnal> getJurnals() {
        return jurnals;
    }

    public void setJurnals(List<Jurnal> jurnals) {
        this.jurnals = jurnals;
    }*/
}




