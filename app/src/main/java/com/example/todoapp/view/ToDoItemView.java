package com.example.todoapp.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.todoapp.R;
import com.example.todoapp.Util;
import com.example.todoapp.model.ToDoItem;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.to_do_item_layout)
public class ToDoItemView extends LinearLayout {

    @ViewById
    ImageView toDoItemIcon;

    @ViewById
    TextView toDoItemDate;

    @ViewById
    TextView toDoItemTitle;

    public ToDoItemView(Context context) {
        super(context);
    }

    /**
     * Binds the to do model to it's view.
     *
     * @param toDoItem ToDoItem model.
     * @return ToDoItem view.
     */
    public ToDoItemView bind(ToDoItem toDoItem) {
        toDoItemTitle.setText(toDoItem.getTitle());
        toDoItemDate.setText(Util.parseDate(toDoItem.getDate()));
        if(toDoItem.isFinished())
            toDoItemIcon.setImageResource(R.drawable.done_mark);
        else
            toDoItemIcon.setImageResource(R.drawable.not_done_mark);

        return this;
    }
}
