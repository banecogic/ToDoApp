package com.example.todoapp.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.todoapp.ToDoApplication;
import com.example.todoapp.model.ToDoItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by FS-LB on 10/25/2016.
 */

public class PreferenceManager {

    private static final PreferenceManager preferenceManager = new PreferenceManager();
    private static final String PREFERENCE_TO_DO_APP_STORE = "ToDoAppStore";
    private static final String PREFERENCE_TO_DO_LIST = "ToDoList";
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    private final Gson gson = new Gson();

    private PreferenceManager(){
        sharedPreferences = ToDoApplication.getAppContext().getSharedPreferences(PREFERENCE_TO_DO_APP_STORE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static PreferenceManager getInstance(){
        return preferenceManager;
    }

    public void addToDoItem(ToDoItem toDoItem) {
        Log.d(this.getClass().getSimpleName(), "Trying to persist a ToDoItem: " + toDoItem + " to the preference store");
        ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>();
        Type listType = new TypeToken<ArrayList<ToDoItem>>(){}.getType();
        String toDoListJson = sharedPreferences.getString(PREFERENCE_TO_DO_LIST, null);
        if(toDoListJson == null){
            Log.d(this.getClass().getSimpleName(), "Preference store has not any ToDoItems yet, so we are creating new ToDoList");
            toDoList.add(toDoItem);
            Log.d(this.getClass().getSimpleName(), "TRACE: toDoListJson: " + gson.toJson(toDoList));
            editor.putString(PREFERENCE_TO_DO_LIST, gson.toJson(toDoList));
            editor.apply();
        }else {
            toDoList = gson.fromJson(toDoListJson, listType);
            toDoList.add(toDoItem);
            editor.putString(PREFERENCE_TO_DO_LIST, gson.toJson(toDoList));
            editor.apply();
        }
    }

    public ArrayList<ToDoItem> getToDoList(){
        Log.d(this.getClass().getSimpleName(), "Trying to retrieve the ToDoList from the preference store");
        String toDoListJson = sharedPreferences.getString(PREFERENCE_TO_DO_LIST, null);
        ArrayList<ToDoItem> retVal = new ArrayList<ToDoItem>();
        Type listType = new TypeToken<ArrayList<ToDoItem>>(){}.getType();
        retVal = toDoListJson!=null?(ArrayList<ToDoItem>) gson.fromJson(toDoListJson, listType):retVal;
        Log.d(this.getClass().getSimpleName(), "TRACE: toDoListJson: " + toDoListJson);
        return retVal;
    }
}
