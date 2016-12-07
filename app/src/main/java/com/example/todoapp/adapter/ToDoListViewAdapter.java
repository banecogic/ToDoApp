package com.example.todoapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.todoapp.model.ToDoItem;
import com.example.todoapp.view.ToDoItemView;
import com.example.todoapp.view.ToDoItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class ToDoListViewAdapter extends BaseAdapter{

    @RootContext
    Context context;

    private final ArrayList<ToDoItem> toDoList = new ArrayList<>();

    public ToDoListViewAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return toDoList.size();
    }

    @Override
    public ToDoItem getItem(int position) {
        return this.toDoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ToDoItem toDoItem = getItem(position);

        if(convertView == null)
            return ToDoItemView_.build(context).bind(toDoItem);
        else
            return ((ToDoItemView) convertView).bind(toDoItem);
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

    public void setToDoItems(List<ToDoItem> toDoItems) {
        toDoList.clear();
        toDoList.addAll(toDoItems);
        notifyDataSetChanged();
    }

    public void deleteToDoItem(ToDoItem toDoItem){
        toDoList.remove(toDoItem);
        notifyDataSetChanged();
    }

    public void addToDoItem(ToDoItem toDoItem){
        toDoList.add(toDoItem);
        notifyDataSetChanged();
    }
}
