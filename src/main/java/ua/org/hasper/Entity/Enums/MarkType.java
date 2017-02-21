package ua.org.hasper.Entity.Enums;

public enum MarkType {
    THEMATIC("Тематическая"),
    CONTROL("Контрольная"),
    VISIT("Посещение");

    private String name;

    MarkType(String name) {
        this.name = name;
    }

    public static MarkType markTypeByName(String name) {
        MarkType tmp = null;
        for (MarkType mt :
                MarkType.values()) {
            if (mt.name.equals(name)) {
                tmp = mt;
            }
        }
        return tmp;

    }

    public String toString() {
        return name;
    }
}
