package com.example.jin2.myapplication.tab1.adapter.push;

import com.example.jin2.myapplication.tab1.adapter.post.post_item;

import java.util.ArrayList;

/**
 * Created by jin on 2017-11-17.
 */

public interface pushContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {

        void addItems(ArrayList<push_item> push_items);

        void clear();
    }

}
