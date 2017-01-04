package ua.org.hasper.Entity;

import ua.org.hasper.Entity.Enums.Mark;
import ua.org.hasper.Entity.Enums.MarkType;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Pavel.Eremenko on 28.09.2016.
 */
@Entity
public class MarkStamp {
    @Id
    @GeneratedValue
    private int id;

    private Calendar createDate;

    @Enumerated(EnumType.STRING)
    private Mark mark;

    @Enumerated(EnumType.STRING)
    private MarkType markType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jurnal_id")
    private Jurnal jurnal;

    public MarkStamp() {
    }

    public MarkStamp( Mark mark, MarkType markType) {
        this.createDate = new GregorianCalendar();
        this.mark = mark;
        this.markType = markType;
    }
    public MarkStamp( Mark mark, MarkType markType, Calendar createDate) {
        this.createDate = createDate;
        this.mark = mark;
        this.markType = markType;
    }

    public Calendar getDate() {
        return createDate;
    }

    public void setDate(Calendar date) {
        this.createDate = date;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public MarkType getMarkType() {
        return markType;
    }

    public void setMarkType(MarkType markType) {
        this.markType = markType;
    }

    public Jurnal getJurnal() {
        return jurnal;
    }

    public void setJurnal(Jurnal jurnal) {
        this.jurnal = jurnal;
        jurnal.addMarkStamp(this);
    }
}

