package com.example.todoapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.todoapp.Constants;
import com.example.todoapp.R;
import com.example.todoapp.Util;
import com.example.todoapp.manager.PreferenceManager;
import com.example.todoapp.model.ToDoItem;
import com.google.gson.Gson;

public class ViewToDoActivity extends AppCompatActivity {
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_to_do);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initializeView();
    }

    private void initializeView() {
        TextView titleContentView = (TextView) findViewById(R.id.title_content_text_view);
        TextView descriptionContentView = (TextView) findViewById(R.id.description_content_text_view);
        TextView dateContentView = (TextView) findViewById(R.id.date_content_text_view);

        Intent intent = getIntent();
        String toDoItemJson = intent.getStringExtra(Constants.TO_DO_ITEM);

        final ToDoItem toDoItem = gson.fromJson(toDoItemJson, ToDoItem.class);
        titleContentView.setText(toDoItem.getTitle());
        descriptionContentView.setText(toDoItem.getDescription());
        dateContentView.setText(Util.parseDate(toDoItem.getDate()));

        final Button markAsDoneBtn = (Button) findViewById(R.id.mark_as_done_button);
        final Button markAsNotDoneBtn = (Button) findViewById(R.id.mark_as_not_done_button);
        Button deleteBtn = (Button) findViewById(R.id.delete_button);

        if(toDoItem.isDone()){
            markAsDoneBtn.setVisibility(View.GONE);
            markAsNotDoneBtn.setVisibility(View.VISIBLE);
        }else{
            markAsDoneBtn.setVisibility(View.VISIBLE);
            markAsNotDoneBtn.setVisibility(View.GONE);
        }

        markAsDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoItem.setDone(true);
                PreferenceManager.getInstance().saveToDoItem(toDoItem, false);
                markAsDoneBtn.setVisibility(View.GONE);
                markAsNotDoneBtn.setVisibility(View.VISIBLE);
            }
        });

        markAsNotDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoItem.setDone(false);
                PreferenceManager.getInstance().saveToDoItem(toDoItem, false);
                markAsDoneBtn.setVisibility(View.VISIBLE);
                markAsNotDoneBtn.setVisibility(View.GONE);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAction(toDoItem);
            }
        });
    }

    private void removeAction(ToDoItem toDoItem) {
        PreferenceManager.getInstance().removeToDoItem(toDoItem);
        Intent backToMainIntent = new Intent(this, MainActivity.class);
        startActivity(backToMainIntent);
    }
}
