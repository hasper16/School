package ua.org.hasper.Entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 02.09.2016.
 */

@Entity
public class ScheduleTimes {

    @Id
    @GeneratedValue
    private int id;

    private int lessonNum;
    private Calendar sdt;
    private int durationMin;

    @OneToMany(mappedBy = "scheduleTimes", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new LinkedList<>();

    public ScheduleTimes() {
    }

    public ScheduleTimes(int lessonNum, Calendar sdt, int durationMin) {
        this.lessonNum = lessonNum;
        this.sdt = sdt;
        this.durationMin = durationMin;
    }

    public int getLessonNum() {
        return lessonNum;
    }

    public void addSchedule(Schedule schedule){
        schedule.setScheduleTimes(this);
        //this.schedules.add(schedule);

    }

    public void setLessonNum(int lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getSdt() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(sdt.getTime());
    }

    public void setSdt(Calendar sdt) {
        this.sdt = sdt;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(LinkedList<Schedule> schedules) {
        this.schedules = schedules;
    }

    public int getId() {
        return id;
    }

}
