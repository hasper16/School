package ua.org.hasper.Entity;

import javax.persistence.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
@Entity
public class Subject implements Comparable<Subject> {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;

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

    public void setId(int id) {
        this.id = id;
    }
}




