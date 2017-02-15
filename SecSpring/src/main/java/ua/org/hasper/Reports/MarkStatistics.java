package ua.org.hasper.Reports;

import org.springframework.context.annotation.ComponentScan;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pavel.Eremenko on 09.02.2017.
 */
public class MarkStatistics {
    private Date reportDate;
    private long countM1, countM2,countM3,countM4,countM5,countM6,countM7,countM8,countM9,countM10,countM11,countM12;

    public MarkStatistics(Date reportDate, long countM1, long countM2, long countM3, long countM4, long countM5, long countM6, long countM7, long countM8, long countM9, long countM10, long countM11, long countM12) {
        this.reportDate = reportDate;
        this.countM1 = countM1;
        this.countM2 = countM2;
        this.countM3 = countM3;
        this.countM4 = countM4;
        this.countM5 = countM5;
        this.countM6 = countM6;
        this.countM7 = countM7;
        this.countM8 = countM8;
        this.countM9 = countM9;
        this.countM10 = countM10;
        this.countM11 = countM11;
        this.countM12 = countM12;
    }

    public String getReportDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(reportDate);
    }

    public long getCountM1() {
        return countM1;
    }

    public long getCountM2() {
        return countM2;
    }

    public long getCountM3() {
        return countM3;
    }

    public long getCountM4() {
        return countM4;
    }

    public long getCountM5() {
        return countM5;
    }

    public long getCountM6() {
        return countM6;
    }

    public long getCountM7() {
        return countM7;
    }

    public long getCountM8() {
        return countM8;
    }

    public long getCountM9() {
        return countM9;
    }

    public long getCountM10() {
        return countM10;
    }

    public long getCountM11() {
        return countM11;
    }

    public long getCountM12() {
        return countM12;
    }
}
