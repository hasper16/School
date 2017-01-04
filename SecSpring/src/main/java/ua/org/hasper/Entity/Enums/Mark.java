package ua.org.hasper.Entity.Enums;

/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
public enum Mark {
    Absent(-1),
    Accept(0),
    m1(1),m2(2),m3(3),m4(4),m5(5),m6(6),m7(7),m8(8),m9(9),m10(10),m11(11),m12(12);

    private final int numMark;
    Mark(int numMark) {
        this.numMark=numMark;
    }
    public String toString() { return Integer.toString(numMark); }

}


