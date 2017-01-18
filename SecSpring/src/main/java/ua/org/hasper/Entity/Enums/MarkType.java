package ua.org.hasper.Entity.Enums;

/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
public enum MarkType {
    THEMATIC ("Тематическая"),
    CONTROL ("Контрольная"),
    VISIT ("Посещение");

    private String name;

    MarkType (String name){
        this.name=name;
    }
    public String toString (){
        return name;
    }
    public static MarkType markTypeFromString (String name){
        MarkType tmp = null;
        for (MarkType mt:
             MarkType.values()) {
            if(mt.name.equals(name)){
                tmp=mt;
            }
        }
        return tmp;

    }
}
