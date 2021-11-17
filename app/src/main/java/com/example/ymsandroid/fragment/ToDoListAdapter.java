package com.example.ymsandroid.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.ymsandroid.R;
import java.util.List;

public class ToDoListAdapter extends BaseAdapter {
    private Context context;
    private List<ToDoList> toDoListList;

    public ToDoListAdapter(Context context, List<ToDoList> toDoListList){
        this.context = context;
        this.toDoListList = toDoListList;
    }

    @Override
    public int getCount() {
        return toDoListList.size();
    }

    @Override
    public Object getItem(int po) {
        return toDoListList.get(po);
    }

    @Override
    public long getItemId(int po) {
        return po;
    }

    @Override
    public View getView(int po, View view, ViewGroup viewGroup) {
        //
        View v = View.inflate(context, R.layout.todolist, null);

        TextView tvListTitle = (TextView)v.findViewById(R.id.tvListTitle);
        TextView tvListCategory = (TextView)v.findViewById(R.id.tvListCategory);
        TextView tvListDate = (TextView)v.findViewById(R.id.tvListDate);
        return v;
    }
}