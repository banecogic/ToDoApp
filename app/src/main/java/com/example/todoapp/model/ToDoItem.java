package com.example.todoapp.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by FS-LB on 10/24/2016.
 */
public class ToDoItem {

    private String title;

    private String description;

    private Date date;

    private boolean isDone;

    public ToDoItem(){
        this("MockTitle", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...");
    }

    public ToDoItem(String title, String description) {
        this(title, description, new Date());
    }

    public ToDoItem(String title, String description, Date date){
        this(title, description, date, false);
    }

    public ToDoItem(String title, String description, Date date, boolean isDone){
        this.title = title;
        this.description = description;
        this.date = date;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String dateString = new Integer(calendar.get(Calendar.DAY_OF_MONTH)) + "/" + new Integer(calendar.get(Calendar.MONTH))
                + "/" + new Integer(calendar.get(Calendar.YEAR));
        return "[ " + title + ", " + dateString + ", "
                + (isDone?" done ]":" to be done ]");
    }
}
