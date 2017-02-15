package ua.org.hasper.Entity.Enums;

/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
public enum Mark {
    Absent(-1,"Отсутсвует",MarkType.VISIT),
    Accept(0,"Принято",MarkType.CONTROL),
    m1(1,"1",MarkType.THEMATIC),
    m2(2,"2",MarkType.THEMATIC),
    m3(3,"3",MarkType.THEMATIC),
    m4(4,"4",MarkType.THEMATIC),
    m5(5,"5",MarkType.THEMATIC),
    m6(6,"6",MarkType.THEMATIC),
    m7(7,"7",MarkType.THEMATIC),
    m8(8,"8",MarkType.THEMATIC),
    m9(9,"9",MarkType.THEMATIC),
    m10(10,"10",MarkType.THEMATIC),
    m11(11,"11",MarkType.THEMATIC),
    m12(12,"12",MarkType.THEMATIC);

    private final int numMark;
    private String name;
    private MarkType markType;
    Mark(int numMark, String name, MarkType markType ) {
        this.numMark=numMark;
        this.name=name;
        this.markType=markType;
    }
    public String toString() { return name; }
    public int getNumMark(){return numMark;}
    public MarkType getMarkType(){return markType;}
    public static Mark MarkByNumMark(int numMark){
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


