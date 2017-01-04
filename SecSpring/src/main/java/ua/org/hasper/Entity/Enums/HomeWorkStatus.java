package ua.org.hasper.Entity.Enums;

import java.util.*;

/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
public enum HomeWorkStatus {

    In_work ("В работе","warning"),
    Done ("Выполнено","success"),
    Assigned ("Задано","danger");

    private String name;
    private String tableClassName;

     HomeWorkStatus(String s,String tableClassName){
         this.name=s;
         this.tableClassName=tableClassName;
     }

    public String toString(){
        return name;
    }
    public String gettableClassName(){return tableClassName;}


}