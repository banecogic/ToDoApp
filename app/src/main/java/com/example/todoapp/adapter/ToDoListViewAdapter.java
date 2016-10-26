package com.example.todoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todoapp.R;
import com.example.todoapp.Util;
import com.example.todoapp.manager.PreferenceManager;
import com.example.todoapp.model.ToDoItem;

import java.util.ArrayList;

public class ToDoListViewAdapter extends BaseAdapter{

    private ArrayList<ToDoItem> toDoList;

    private Context context;

    public ToDoListViewAdapter(Context context){
        this.context = context;
        initToDoList();
    }

    private void initToDoList() {
        this.toDoList = PreferenceManager.getInstance().getToDoList();
    }

    @Override
    public int getCount() {
        return toDoList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.toDoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflanter = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflanter.inflate(R.layout.to_do_item_layout, parent, false);
        TextView titleView = (TextView) rowView.findViewById(R.id.to_do_item_title);
        TextView dateView = (TextView) rowView.findViewById(R.id.to_do_item_date);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.to_do_item_icon);

        ToDoItem toDoItem = this.toDoList.get(position);
        titleView.setText(toDoItem.getTitle());

        String dateString = Util.parseDate(toDoItem.getDate());
        dateView.setText(dateString);

        imageView.setImageResource(toDoItem.isDone()?R.drawable.done_mark:R.drawable.not_done_mark);
        return rowView;
    }

    public ArrayList<ToDoItem> getToDoList() {
        return toDoList;
    }

    /**
     * Searches ToDoList for ToDoItem with the given title
     *
     * @param title wanted ToDoItem's title
     * @return ToDoItem with coresponding title
     */
    public ToDoItem getToDoItemByTitle(String title){
        for(ToDoItem toDoItem : toDoList){
            if(toDoItem.getTitle().equals(title))
                return toDoItem;
        }
        return null;
    }
}
