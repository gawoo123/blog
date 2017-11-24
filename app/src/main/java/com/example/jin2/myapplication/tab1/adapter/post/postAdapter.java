package com.example.jin2.myapplication.tab1.adapter.post;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jin2.myapplication.R;

import java.util.ArrayList;

/**
 * Created by jin on 2017-10-31.
 */

public class postAdapter extends BaseAdapter implements postContract.Model,postContract.View {

    ArrayList<post_item> postItemArrayList = new ArrayList<post_item>();

    @Override
    public int getCount() {
        return postItemArrayList.size();
    }

    @Override
    public post_item getItem(int position) {
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
        TextView url = (TextView) convertView.findViewById(R.id.post_url);

        post_item item = postItemArrayList.get(position);
        date.setText(item.getDate());
        url.setText(item.getUrl());

        return convertView;
    }

    @Override
    public void addItems(ArrayList<post_item> post_items) {
        this.postItemArrayList=post_items;
    }

    @Override
    public void clear() {
        this.postItemArrayList=null;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }
}