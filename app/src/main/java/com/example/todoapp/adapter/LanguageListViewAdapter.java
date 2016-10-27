package com.example.todoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todoapp.R;
import com.example.todoapp.ToDoApplication;

import java.util.ArrayList;
import java.util.Locale;

public class LanguageListViewAdapter extends BaseAdapter {

    public class Tuple<X, Y, Z> {
        public final X first;
        public final Y second;
        public final Z third;

        public Tuple(X first, Y second, Z third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    private ArrayList<Tuple<Integer, Integer, Locale>> languageList = new ArrayList<Tuple<Integer, Integer, Locale>>();

    private Context context;

    public LanguageListViewAdapter(Context context){
        this.context = context;
        populateLanguageTuples();
    }

    @Override
    public int getCount() {
        return languageList.size();
    }

    @Override
    public Object getItem(int position) {
        return languageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflanter = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflanter.inflate(R.layout.language_item_layout, parent, false);

        TextView languageName = (TextView) rowView.findViewById(R.id.language_item_text);
        ImageView icon = (ImageView) rowView.findViewById(R.id.language_item_icon);

        Tuple<Integer, Integer, Locale> language = languageList.get(position);

        languageName.setText(language.first);
        icon.setImageResource(language.second);

        return rowView;
    }

    private void populateLanguageTuples() {
        languageList.add(new Tuple<Integer, Integer, Locale>(R.string.english, R.drawable.flag_united_kingdom, Locale.UK));
        languageList.add(new Tuple<Integer, Integer, Locale >(R.string.italian, R.drawable.flag_italy, Locale.ITALY));
        languageList.add(new Tuple<Integer, Integer, Locale >(R.string.serbian, R.drawable.flag_serbia, new Locale("sr", "sr_Latn_RS")));
    }

}
