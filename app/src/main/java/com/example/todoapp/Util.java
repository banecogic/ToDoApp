package com.example.todoapp;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Util {
    public static String parseDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String dateString = new Integer(calendar.get(Calendar.DAY_OF_MONTH)) + "/" + new Integer(calendar.get(Calendar.MONTH))
                + "/" + new Integer(calendar.get(Calendar.YEAR));
        return dateString;
    }

    public static void changeLocale(Locale locale){
        Configuration config = new Configuration();
        config.locale = locale;
        Resources resources = ToDoApplication.getAppContext().getResources();
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
