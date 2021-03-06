package com.example.todoapp.model.dto;

/**
 * Created by FS-LB on 12/7/2016.
 */

public class ToDoItemDTO {

    private String title;

    private String description;

    public ToDoItemDTO() {
    }

    public ToDoItemDTO(String title, String description) {
        this.title = title;
        this.description = description;
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
}
