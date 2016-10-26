package com.example.todoapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.todoapp.Constants;
import com.example.todoapp.R;
import com.example.todoapp.adapter.ToDoListViewAdapter;
import com.example.todoapp.model.ToDoItem;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    ToDoListViewAdapter toDoListViewAdapter;

    ListView toDoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
    }

    /**
     * Everytime the activity start we fetch all ToDoItems that the user have so we can show all
     * of them in the ListView
     */
    @Override
    protected void onStart() {
        super.onStart();
        toDoListViewAdapter = new ToDoListViewAdapter(this.getApplicationContext());
        toDoListView.setAdapter(toDoListViewAdapter);
    }

    /**
     * Initialize view components in the MainActivity.
     * Actually just add onClick listener to the Add new to do button and save reference of to dos ListView
     */
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
    }

    /**
     * Opens a new AddToDoActivity. Called on Add button click from MainActivity
     */
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
}
