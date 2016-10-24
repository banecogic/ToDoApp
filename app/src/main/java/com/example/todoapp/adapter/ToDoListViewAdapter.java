package com.example.todoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todoapp.R;
import com.example.todoapp.model.ToDoItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ToDoListViewAdapter extends BaseAdapter{

    private ArrayList<ToDoItem> toDoList;

    private Context context;

    public ToDoListViewAdapter(Context context){
        this.context = context;
        initToDoList();
    }

    private void initToDoList() {
        ToDoItem tdi1 = new ToDoItem("Go to gym", "You need to become a regalčina!", new Date());
        ToDoItem tdi2 = new ToDoItem("Go to work", "You need to become a millionaire !!!", new Date());
        ToDoItem tdi3 = new ToDoItem("Go to faculty", "You need to become a PhD !!!!", new Date());
        ToDoItem tdi4 = new ToDoItem("Go to hackaton", "You gotta hack them all !!!!", new Date());
        ToDoItem tdi5 = new ToDoItem("Go to bed", "You need some rest mate !", new Date());

        this.toDoList = new ArrayList<ToDoItem>();
        this.toDoList.add(tdi1);
        this.toDoList.add(tdi2);
        this.toDoList.add(tdi3);
        this.toDoList.add(tdi4);
        this.toDoList.add(tdi5);
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

        String dateString = formatDateToStringRepresentation(toDoItem.getDate());
        dateView.setText(dateString);

        imageView.setImageResource(position%2==0?R.drawable.done_mark:R.drawable.not_done_mark);
        return rowView;
    }

    private String formatDateToStringRepresentation(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String retVal = new Integer(calendar.get(Calendar.DAY_OF_MONTH)) + "/" + new Integer(calendar.get(Calendar.MONTH))
                + new Integer(calendar.get(Calendar.YEAR));
        return retVal;
    }
}
