package com.example.jin2.myapplication.tab1.adapter.post;

import com.example.jin2.myapplication.retrofit.Contributor;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by jin on 2017-11-17.
 */

public interface postContract {

    interface View {

        void notifyAdapter();

    }

    interface Model {

        void addItems(ArrayList<post_item> post_items);

        void clear();

        post_item getItem(int pos);

    }

}
