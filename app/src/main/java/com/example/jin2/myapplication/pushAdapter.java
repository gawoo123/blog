package com.example.jin2.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jin on 2017-10-31.
 */

public class pushAdapter extends BaseAdapter {

    ArrayList<push_item> push_list = new ArrayList<push_item>();
    @Override
    public int getCount() {
        return push_list.size();
    }

    @Override
    public Object getItem(int position) {
        return push_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.push_item, parent, false);
        }

        TextView date = (TextView) convertView.findViewById(R.id.push_date);

        push_item item = push_list.get(position);
        date.setText(item.getDate());

        return convertView;
    }

    public void addItem(String date, String idx) {
        push_item item = new push_item();

        item.setDate(date);
        item.setIdx(idx);

        push_list.add(item);
    }

}
