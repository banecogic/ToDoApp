package com.example.todoapp.model;

import com.example.todoapp.Util;
import java.util.Date;



public class ToDoItem {

    private long id;

    private String title;

    private String description;

    private Date date;

    private boolean finished;

    public ToDoItem(){
        this("MockTitle", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...");
    }

    public ToDoItem(String title, String description) {
        this(title, description, new Date());
    }

    public ToDoItem(String title, String description, Date date){
        this(title, description, date, false);
    }

    public ToDoItem(String title, String description, Date date, boolean finished){
        this.title = title;
        this.description = description;
        this.date = date;
        this.finished = finished;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        String dateString = Util.parseDate(date);
        return "[ " + title + ", " + dateString + ", "
                + (finished?" done ]":" to be done ]");
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ToDoItem))
            return false;
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(this.getTitle().equals(((ToDoItem) obj).getTitle()))
            return true;
        return false;
    }
}
