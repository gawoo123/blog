package com.example.jin2.myapplication;

import android.app.Fragment;
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

public class postAdapter extends BaseAdapter {

    ArrayList<post_item> postItemArrayList = new ArrayList<post_item>();

    @Override
    public int getCount() {
        return postItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return postItemArrayList.get(position);
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
            convertView = inflater.inflate(R.layout.post_item, parent, false);
        }

        final TextView date = (TextView) convertView.findViewById(R.id.post_date);

        post_item item = postItemArrayList.get(position);
        date.setText(item.getDate());

//        date.setOn(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               System.out.println("눌림 --> "+postItemArrayList.get(pos).getUrl());
//            }
//        });



        return convertView;
    }

    public void addItem(String date, String url) {
        post_item item = new post_item();

        item.setDate(date);
        item.setUrl(url);

        postItemArrayList.add(item);
    }


}