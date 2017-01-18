package ua.org.hasper.Entity.Enums;

/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
public enum Mark {
    Absent(-1,"Отсутсвует"),
    Accept(0,"Принято"),
    m1(1,"1"),
    m2(2,"2"),
    m3(3,"3"),
    m4(4,"4"),
    m5(5,"5"),
    m6(6,"6"),
    m7(7,"7"),
    m8(8,"8"),
    m9(9,"9"),
    m10(10,"10"),
    m11(11,"11"),
    m12(12,"12");

    private final int numMark;
    private String name;
    Mark(int numMark, String name ) {
        this.numMark=numMark;
        this.name=name;
    }
    public String toString() { return name; }
    public int getNumMark(){return numMark;}
    public static Mark MarkFromIntValue(int numMark){
        Mark tmp = null;
        for (Mark m:
                Mark.values()) {
            if(m.numMark==numMark){
                tmp=m;
            }
        }
        return tmp;
    }

}


