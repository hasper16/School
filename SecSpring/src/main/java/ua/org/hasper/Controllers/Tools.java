package ua.org.hasper.Controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pavel.Eremenko on 25.01.2017.
 */
public class Tools {
    public static Calendar parteHTMLDate (String date){
        try {
            Calendar cal;
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date d = formatter.parse(date);
            cal = Calendar.getInstance();
            cal.setTime(d);
            return cal;
        }catch (ParseException pe){
            return null;
        }


    }
}
