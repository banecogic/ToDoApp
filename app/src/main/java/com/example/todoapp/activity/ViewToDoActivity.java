package com.example.todoapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.todoapp.Constants;
import com.example.todoapp.R;
import com.example.todoapp.model.ToDoItem;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_view_to_do)
public class ViewToDoActivity extends AppCompatActivity {

    private static final String TAG = ViewToDoActivity.class.getSimpleName();

    @ViewById
    TextView title;

    @ViewById
    TextView description;

    @ViewById
    Button markAsDoneBtn;

    @ViewById
    Button markAsNotDoneBtn;

    @ViewById
    Button deleteBtn;

    @Extra(Constants.TO_DO_ITEM)
    String toDoItemJson;

    Gson gson = new Gson();

    private ToDoItem toDoItem;

    @AfterViews
    void initializeView() {
        toDoItem = gson.fromJson(toDoItemJson, ToDoItem.class);
        title.setText(toDoItem.getTitle());
        description.setText(toDoItem.getDescription());

        if(toDoItem.isFinished()){
            markAsDoneBtn.setVisibility(View.GONE);
            markAsNotDoneBtn.setVisibility(View.VISIBLE);
        }else{
            markAsDoneBtn.setVisibility(View.VISIBLE);
            markAsNotDoneBtn.setVisibility(View.GONE);
        }

    }

    @Click
    void markAsDoneBtn() {
        Log.i(TAG, "ToDoItem marked as done...");
        toDoItem.setFinished(true);
        markAsDoneBtn.setVisibility(View.GONE);
        markAsNotDoneBtn.setVisibility(View.VISIBLE);
        //TODO: Call rest to mark to do as done
    }

    @Click
    void markAsNotDoneBtn() {
        Log.i(TAG, "ToDoItem marked as not done...");
        toDoItem.setFinished(false);
        markAsDoneBtn.setVisibility(View.VISIBLE);
        markAsNotDoneBtn.setVisibility(View.GONE);
        //TODO: Call rest to mark to do as not done
    }

    @Click
    void deleteBtn() {
        //TODO Call rest to delete ToDoItem
        Log.i(TAG, "Delete button clicked. Starting MainActivity...");

        setResult(RESULT_OK);
        finish();
    }
}
