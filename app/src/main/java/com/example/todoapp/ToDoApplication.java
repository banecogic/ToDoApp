package com.example.todoapp;

import android.app.Application;
import android.content.Context;

public class ToDoApplication extends Application {

    private static ToDoApplication toDoApp;

    @Override
    public void onCreate() {
        super.onCreate();
        toDoApp = this;
    }

    public static Context getAppContext(){
        return toDoApp.getApplicationContext();
    }
}
