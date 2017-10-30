package com.example.jin2.myapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import java.util.ArrayList;

/**
 * Created by jin2 on 2017-10-18.
 */

public class myInfo extends Fragment{

    public static myInfo newInstance() {
        myInfo fragment = new myInfo();
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.my_info,container,false);
        final ArrayList<String> items = new ArrayList<String>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_single_choice, items) ;

        items.add("네이버 계정");
        items.add("카테고리 설정");

        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) view.findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }



}
