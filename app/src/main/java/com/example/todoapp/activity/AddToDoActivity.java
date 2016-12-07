package com.example.todoapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.todoapp.R;
import com.example.todoapp.model.ToDoItem;
import com.google.gson.Gson;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

@EActivity(R.layout.activity_add_to_do)
public class AddToDoActivity extends AppCompatActivity {

    private static final String TAG = AddToDoActivity.class.getSimpleName();

    @ViewById
    EditText title;

    @ViewById
    EditText description;

    @ViewById
    Button add;

    @Click
    void add() {
        Log.d(TAG, "Add button clicked. Going back to MainActivity...");
        final ToDoItem toDoItem = new ToDoItem(title.getText().toString(), description.getText().toString(), new Date(), false);
        final Intent intent = new Intent();
        final Gson gson = new Gson();
        intent.putExtra("toDoItem", gson.toJson(toDoItem));
        setResult(RESULT_OK, intent);
        finish();
    }

}
