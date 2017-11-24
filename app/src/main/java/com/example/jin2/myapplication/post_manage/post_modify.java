package com.example.jin2.myapplication.post_manage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jin2.myapplication.R;

/**
 * Created by jin on 2017-11-21.
 */

public class post_modify extends Fragment{

    static int post_idx;
    View view;

    EditText title;
    EditText start_tv;
    EditText content1_tv;
    EditText content2_tv;
    EditText content3_tv;
    EditText end_tv;



    public static post_modify newInstance(int idx) {
        post_idx=idx;
        post_modify fragment = new post_modify();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.modify,container,false);

        // setonclicklustner -> 이전 프래그먼트

         title = (EditText) view.findViewById(R.id.title_tv);
         start_tv = (EditText) view.findViewById(R.id.start_tv);
         content1_tv = (EditText) view.findViewById(R.id.content1_tv);
         content2_tv = (EditText) view.findViewById(R.id.content2_tv);
         content3_tv = (EditText) view.findViewById(R.id.content3_tv);
         end_tv = (EditText) view.findViewById(R.id.end_tv);




        return view;
    }
}
