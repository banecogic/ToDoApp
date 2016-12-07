package com.example.todoapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.todoapp.Constants;
import com.example.todoapp.R;
import com.example.todoapp.adapter.ToDoListViewAdapter;
import com.example.todoapp.api.RestApi;
import com.example.todoapp.model.ToDoItem;
import com.example.todoapp.model.dto.ToDoItemDTO;
import com.example.todoapp.preference.UserPreference_;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     *  Used for identifying results from different activities.
     */
    public static final int LOGIN_REQUEST_CODE = 1;
    public static final int ADD_TO_DO_REQUEST_CODE = 2;
    public static final int VIEW_TO_DO_REQUEST_CODE = 3;

    private List<ToDoItem> toDoItems;


    @ViewById
    ImageButton settingsButton;

    @ViewById
    ImageButton addToDo;

    @ViewById
    ListView toDoList;

    @Bean
    ToDoListViewAdapter toDoListViewAdapter;

    @Pref
    UserPreference_ userPreference;

    @RestService
    RestApi restApi;

    @AfterViews
    @Background
    void checkIfUserIsLoggedIn() {
        Log.i(TAG, "Checking if user is logged in...");
        if(!userPreference.accessToken().exists()){
            Log.i(TAG, "User is not logged in. Starting LoginActivity...");
            LoginActivity_.intent(this).startForResult(LOGIN_REQUEST_CODE);
            return;
        }

        Log.i(TAG, "User is logged in. Fetching to do items for the user...");

        try{
            toDoItems = restApi.getAllToDoItems();
            Log.i(TAG, "Successfully fetched to do items. Fetched " + toDoItems.size() + " to do items");
        } catch (Exception e) {
            Log.e(TAG, "Fetching to do items failed. Exception message:\n\t" + e.getMessage(), e);
        }

        initData();
    }

    @UiThread
    void initData() {
        toDoList.setAdapter(toDoListViewAdapter);
        toDoListViewAdapter.setToDoItems(toDoItems);
    }

    @Click
    void addToDo() {
        Log.i(TAG, "Add task clicked. Starting AddToDoActivity...");
        AddToDoActivity_.intent(this).startForResult(ADD_TO_DO_REQUEST_CODE);
    }

    @ItemClick(value = R.id.toDoList)
    void toDoListItemClicked(int position) {
        ToDoItem toDoItem = toDoListViewAdapter.getItem(position);
        Gson gson = new Gson();
        ViewToDoActivity_.intent(this).extra(Constants.TO_DO_ITEM, gson.toJson(toDoItem, ToDoItem.class)).startForResult(VIEW_TO_DO_REQUEST_CODE);
    }

    @OnActivityResult(LOGIN_REQUEST_CODE)
    @Background
    void onLogin(int resultCode, @OnActivityResult.Extra("token") String token) {
        if(resultCode == RESULT_OK) {
            userPreference.accessToken().put(token);
            checkIfUserIsLoggedIn();
        }
    }

    @OnActivityResult(ADD_TO_DO_REQUEST_CODE)
    @Background
    void onResult(int resultCode, @OnActivityResult.Extra("toDoItem") String toDoItem){
        if(resultCode == RESULT_OK){
            final Gson gson = new Gson();
            final ToDoItem newToDoItem = gson.fromJson(toDoItem, ToDoItem.class);
            try {
                Log.i(TAG, "Sending rest call to create new ToDoItem");
                restApi.createToDoItem(new ToDoItemDTO(newToDoItem.getTitle(), newToDoItem.getDescription()));
                onCreateToDoItemSucceed(newToDoItem);
            } catch (Exception e) {
                onCreateToDoItemFailed(e);
            }
        }
    }

    @UiThread
    void onCreateToDoItemSucceed(ToDoItem toDoItem) {
        Log.i(TAG, "Successfully created new ToDoItem " + toDoItem.toString());
        toDoListViewAdapter.addToDoItem(toDoItem);
    }

    @UiThread
    void onCreateToDoItemFailed(Exception e){
        Toast.makeText(this, "Failed to create new ToDoItem. Probably network error.", Toast.LENGTH_SHORT);
        Log.e(TAG, "Failed to create new ToDoItem. Exception detail:\n\t" + e.getMessage());
        e.printStackTrace();
    }

    /**
     * Initialize view components in the MainActivity.
     * Actually just add onClick listener to the Add new to do button and save reference of to dos ListView
     */
    /*
    private void initializeView() {
        ListView listView = (ListView) findViewById(R.id.to_do_list);
        toDoListView = listView;
        ImageButton addToDoBtn = (ImageButton) findViewById(R.id.add_to_do_button);
        addToDoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewToDoAction();
            }
        });
        toDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewToDoAction(view);
            }
        });

        ImageButton settingsBtn = (ImageButton) findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsAction();
            }
        });
    }*/

    /**
     * Opens a new AddToDoActivity. Called on Add button click from MainActivity
     */
    /*
    private void addNewToDoAction(){
        Intent addToDoIntent = new Intent(this, AddToDoActivity.class);
        startActivityForResult(addToDoIntent, 0);
    }

    private void viewToDoAction(View view){
        ToDoItem toDoItem = toDoListViewAdapter.getToDoItemByTitle(((TextView) view.findViewById(R.id.to_do_item_title)).getText().toString());
        Intent viewToDoItemIntent = new Intent(this, ViewToDoActivity.class);
        Gson gson = new Gson();
        viewToDoItemIntent.putExtra(Constants.TO_DO_ITEM, gson.toJson(toDoItem, ToDoItem.class));
        startActivity(viewToDoItemIntent);
    }

    private void settingsAction(){
        Intent goToSettingsIntent = new Intent(this, ChooseLanguageActivity.class);
        Util.changeLocale(new Locale(""));
        goToSettingsIntent.putExtra(Constants.COMMING_FROM_MAIN, true);
        startActivity(goToSettingsIntent);
    }*/
}
