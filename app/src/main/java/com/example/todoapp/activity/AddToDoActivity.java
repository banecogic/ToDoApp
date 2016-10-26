package com.example.todoapp.activity;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.DisplayContext;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.todoapp.R;
import com.example.todoapp.manager.PreferenceManager;
import com.example.todoapp.model.ToDoItem;

import java.util.Date;

public class AddToDoActivity extends AppCompatActivity {

    private boolean isTitleTouched = false;
    private boolean isDescriptionTouched = false;

    private final String PREVIOUSLY_TYPED_TITLE = "previouslyTypedTitle";
    private final String PREVIOUSLY_TYPED_DESCRIPTION = "previouslyTypedDescription";

    private EditText title;
    private EditText description;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        sharedPreferences = getPreferences(MODE_PRIVATE);

        initViewComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();

        String previousTitle = sharedPreferences.getString(PREVIOUSLY_TYPED_TITLE, null);
        String previousDescription = sharedPreferences.getString(PREVIOUSLY_TYPED_DESCRIPTION, null);

        if(previousTitle!=null){
            EditText title = (EditText) findViewById(R.id.new_to_do_title);
            title.setText(previousTitle);
        }
        if(previousDescription!=null){
            EditText description = (EditText) findViewById(R.id.new_to_do_description);
            description.setText(previousDescription);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        EditText title = (EditText) findViewById(R.id.new_to_do_title);
        EditText description = (EditText) findViewById(R.id.new_to_do_description);

        editor.putString(PREVIOUSLY_TYPED_TITLE, title.getText().toString());
        editor.putString(PREVIOUSLY_TYPED_DESCRIPTION, description.getText().toString());
        editor.commit();
    }


    private void initViewComponents() {

        final EditText title = (EditText) findViewById(R.id.new_to_do_title);
        this.title = title;
        final EditText description = (EditText) findViewById(R.id.new_to_do_description);
        this.description = description;
        final Button addBtn = (Button) findViewById(R.id.add_button);

        title.setTypeface(null, Typeface.BOLD_ITALIC);

        title.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!isTitleTouched){
                    isTitleTouched = true;
                    title.setTextColor(Color.parseColor("#000000"));
                    title.setTypeface(null, Typeface.BOLD);
                    title.setText("");
                }
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                return false;
            }
        });

        description.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!isDescriptionTouched){
                    isDescriptionTouched = true;
                    description.setTextColor(Color.parseColor("#000000"));
                    description.setTypeface(null, Typeface.NORMAL);
                    description.setText("");
                }
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                return false;
            }
        });

        title.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText view = null;
                if(v instanceof EditText)
                    view = (EditText) v;

                // IF TITLE VIEW LOSE FOCUS AND IS EMPTY PUT THE PLACEHOLDER FOR THE TITLE BACK
                if(view != null && view.getText().length()==0 && !hasFocus) {
                    view.setText(R.string.title_placeholder);
                    view.setTypeface(null, Typeface.BOLD_ITALIC);
                    int color = getResources().getColor(R.color.colorPlaceholder);
                    view.setTextColor(color);
                    isTitleTouched = false;
                }
            }
        });

        description.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText view = null;
                if(v instanceof EditText)
                    view = (EditText) v;

                // IF DESCRIPTION VIEW LOSE FOCUS AND IS EMPTY PUT THE PLACEHOLDER FOR THE DESCRIPTION BACK
                if(view != null && view.getText().length()==0 && !hasFocus) {
                    view.setText(R.string.description_placeholder);
                    view.setTypeface(null, Typeface.ITALIC);
                    int color = getResources().getColor(R.color.colorPlaceholder);
                    view.setTextColor(color);
                    isDescriptionTouched = false;
                }
            }
        });

        final Intent backToMainIntent = new Intent(this, MainActivity.class);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoItem newToDoItem = new ToDoItem(title.getText().toString(), description.getText().toString(), new Date(), false);
                PreferenceManager.getInstance().addToDoItem(newToDoItem);

                /* TODO: MOVE THIS PIECE OF CODE. PUT PLACEHOLDERS BEFORE FINISH PROBABLY ON STOP.
                CURRENTLY THIS MAKES A "FLASHING" EFFECT PRIOR TO SWITCHING BACK TO MAIN THREAD */
                title.setText(R.string.title_placeholder);
                title.setTypeface(null, Typeface.BOLD_ITALIC);
                int color = getResources().getColor(R.color.colorPlaceholder);
                title.setTextColor(color);
                isTitleTouched = false;
                description.setText(R.string.description_placeholder);
                description.setTypeface(null, Typeface.ITALIC);
                description.setTextColor(color);
                isDescriptionTouched = false;
                finish();
            }
        });
    }
}
