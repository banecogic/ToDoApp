package com.example.todoapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by FS-LB on 10/25/2016.
 */

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
