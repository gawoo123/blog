package com.example.jin2.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by jin2 on 2017-10-18.
 */

public class test extends Fragment{

    public static test newInstance() {
        test fragment = new test();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        Toast.makeText(getContext(),"tttttt",Toast.LENGTH_LONG).show();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
       return inflater.inflate(R.layout.detail_push,container,false);
    }
}
