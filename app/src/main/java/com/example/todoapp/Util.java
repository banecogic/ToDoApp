package com.example.todoapp;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by FS-LB on 10/26/2016.
 */

public class Util {
    public static String parseDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String dateString = new Integer(calendar.get(Calendar.DAY_OF_MONTH)) + "/" + new Integer(calendar.get(Calendar.MONTH))
                + "/" + new Integer(calendar.get(Calendar.YEAR));
        return dateString;
    }
}
