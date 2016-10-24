package com.example.todoapp.model;

import java.util.Date;

/**
 * Created by FS-LB on 10/24/2016.
 */
public class ToDoItem {

    private String title;

    private String description;

    private Date date;

    public ToDoItem(){
        this.title = "Mock title";
        this.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...";
        this.date = new Date();
    }

    public ToDoItem(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
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
}
