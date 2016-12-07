package com.example.todoapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.todoapp.R;
import com.example.todoapp.ToDoApplication;
import com.example.todoapp.Util;
import com.example.todoapp.adapter.LanguageListViewAdapter;

import java.util.Locale;

public class ChooseLanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    /**
     * Initialize view components in the MainActivity.
     * Actually just add onClick listener to the Add new to do button and save reference of to dos ListView
     */
    private void initializeView() {
        ListView languageListView = (ListView) findViewById(R.id.language_list);
        languageListView.setAdapter(new LanguageListViewAdapter(ToDoApplication.getAppContext()));
        languageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LanguageListViewAdapter languageListViewAdapter = (LanguageListViewAdapter) parent.getAdapter();
                languageChoosenAction(view, languageListViewAdapter, position);
            }
        });

    }

    private void languageChoosenAction(View view, LanguageListViewAdapter languageListViewAdapter, int position){
        TextView languageName = (TextView) view.findViewById(R.id.language_item_text);
        ImageView icon = (ImageView) view.findViewById(R.id.language_item_icon);
        LanguageListViewAdapter.Tuple tuple = (LanguageListViewAdapter.Tuple) languageListViewAdapter.getItem(position);
        Locale locale = (Locale) tuple.third;
        Util.changeLocale(locale);
        Intent goToMainIntent = new Intent(this, MainActivity.class);
        //PreferenceManager.getInstance().setLanguageSetting(locale.getLanguage());
        //PreferenceManager.getInstance().setCountrySetting(locale.getCountry());
        startActivity(goToMainIntent);
    }
}
