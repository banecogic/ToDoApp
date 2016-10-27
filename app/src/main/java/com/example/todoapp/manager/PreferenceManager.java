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

public class PreferenceManager {

    private static final PreferenceManager preferenceManager = new PreferenceManager();
    private static final String PREFERENCE_TO_DO_APP_STORE = "ToDoAppStore";
    private static final String PREFERENCE_TO_DO_LIST = "ToDoList";
    private static final String PREFERENCE_LANGUAGE_SETTING = "Language";
    private static final String PREFERENCE_COUNTRY_SETTING = "Country";
    private static final String PREFERENCE_FIRST_OPENING = "FirstOpening";
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

    /**
     * Tries to save ToDoItem to the preference store.
     * Parameter <code>isNew</code> should be set to true if method is called from AddToDoActivity and
     * set to false if it's called from ViewToDoActivity
     *
     * @param toDoItem ToDoItem to save
     * @param isNew indicate if ToDoItem is saving for the first time
     * @return true if successfully saved, false if not
     */
    public boolean saveToDoItem(ToDoItem toDoItem, boolean isNew) {
        Log.d(this.getClass().getSimpleName(), "Trying to save a ToDoItem: " + toDoItem + " to the preference store");
        ArrayList<ToDoItem> toDoList = new ArrayList<ToDoItem>();
        Type listType = new TypeToken<ArrayList<ToDoItem>>(){}.getType();
        String toDoListJson = sharedPreferences.getString(PREFERENCE_TO_DO_LIST, null);
        if(toDoListJson == null){
            Log.d(this.getClass().getSimpleName(), "Preference store has not any ToDoItems yet, so we are creating new ToDoList");
            toDoList.add(toDoItem);
            Log.d(this.getClass().getSimpleName(), "TRACE: toDoListJson: " + gson.toJson(toDoList));
            editor.putString(PREFERENCE_TO_DO_LIST, gson.toJson(toDoList));
        }else {
            toDoList = gson.fromJson(toDoListJson, listType);
            for(ToDoItem item : toDoList ){
                if(item.getTitle().equals(toDoItem.getTitle())){
                    if(isNew)
                        return false;
                    else
                        item.setDone(toDoItem.isDone());
                }
            }
            if(isNew)
                toDoList.add(toDoItem);
            editor.putString(PREFERENCE_TO_DO_LIST, gson.toJson(toDoList));
        }
        editor.apply();
        return true;
    }

    /**
     * Tries to remove ToDoItem from the preference store.
     *
     * @param toDoItem ToDoItem to remove
     * @return true if successfully removed, false if not
     */
    public boolean removeToDoItem(ToDoItem toDoItem) {
        ArrayList<ToDoItem> toDoList;
        Type listType = new TypeToken<ArrayList<ToDoItem>>(){}.getType();
        String toDoListJson = sharedPreferences.getString(PREFERENCE_TO_DO_LIST, null);
        toDoList = gson.fromJson(toDoListJson, listType);
        for(ToDoItem item : toDoList ){
            if(item.getTitle().equals(toDoItem.getTitle())){
                toDoList.remove(item);
                editor.putString(PREFERENCE_TO_DO_LIST, gson.toJson(toDoList));
                editor.apply();
                return true;
            }
        }
        return false;
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

    public String getLanguageSetting(){
        return sharedPreferences.getString(PREFERENCE_LANGUAGE_SETTING, null);
    }

    public void setLanguageSetting(String value) {
        editor.putString(PREFERENCE_LANGUAGE_SETTING, value);
        editor.apply();
    }

    public String getCountrySetting(){
        return sharedPreferences.getString(PREFERENCE_COUNTRY_SETTING, null);
    }

    public void setCountrySetting(String value) {
        editor.putString(PREFERENCE_COUNTRY_SETTING, value);
        editor.apply();
    }

    public boolean isFirstOpening(){
        boolean retVal = sharedPreferences.getBoolean(PREFERENCE_FIRST_OPENING, true);
        if(retVal){
            editor.putBoolean(PREFERENCE_FIRST_OPENING, false);
            editor.apply();
        }
        return retVal;
    }
}
