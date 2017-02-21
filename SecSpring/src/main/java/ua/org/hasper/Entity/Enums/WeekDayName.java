package ua.org.hasper.Entity.Enums;

import java.util.Calendar;

public enum WeekDayName {
    Monday("Понедельник", Calendar.MONDAY),
    Tuesday("Вторник", Calendar.THURSDAY),
    Wednesday("Среда", Calendar.WEDNESDAY),
    Thursday("Четверг", Calendar.THURSDAY),
    Friday("Пятница", Calendar.FRIDAY),
    Saturday("Суббота", Calendar.SATURDAY),
    Sunday("Воскресенье", Calendar.SUNDAY);

    private String name;
    private int calendarWeekDay;

    WeekDayName(String name, int calendarWeekDay) {
        this.name = name;
        this.calendarWeekDay = calendarWeekDay;
    }

    public static WeekDayName getDayByName(String name) {
        WeekDayName res = null;
        for (WeekDayName d :
                WeekDayName.values()) {
            if (d.toString().equals(name)) {
                res = d;
            }
        }
        return res;
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getcalendarWeekDay() {
        return calendarWeekDay;
    }
}
