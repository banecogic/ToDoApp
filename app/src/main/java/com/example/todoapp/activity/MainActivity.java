package com.example.todoapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.todoapp.R;
import com.example.todoapp.adapter.ToDoListViewAdapter;

public class MainActivity extends AppCompatActivity {
    ToDoListViewAdapter toDoListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.to_do_list);
        toDoListViewAdapter = new ToDoListViewAdapter(this.getApplicationContext());
        listView.setAdapter(toDoListViewAdapter);
    }
}
