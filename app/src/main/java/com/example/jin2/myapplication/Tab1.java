package com.example.jin2.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jin2.myapplication.post_manage.Preview;
import com.example.jin2.myapplication.post_manage.post_link;
import com.example.jin2.myapplication.retrofit.Contributor;
import com.example.jin2.myapplication.retrofit.RetrofitHelper;
import com.example.jin2.myapplication.tab1.Tab1Contract;
import com.example.jin2.myapplication.tab1.Tab1Presenter;
import com.example.jin2.myapplication.tab1.adapter.data.postData;
import com.example.jin2.myapplication.tab1.adapter.data.pushData;
import com.example.jin2.myapplication.tab1.adapter.post.postAdapter;
import com.example.jin2.myapplication.tab1.adapter.post.post_item;
import com.example.jin2.myapplication.tab1.adapter.push.pushAdapter;
import com.example.jin2.myapplication.tab1.adapter.push.push_item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jin2 on 2017-10-18.
 */

public class Tab1 extends Fragment implements Tab1Contract.View{


    ListView push_list;
    ListView post_list;

    List<String> push_item;
    List<String> post_item;
    pushAdapter push_adapter;
    postAdapter post_adapter;



    final com.example.jin2.myapplication.tab1.adapter.post.post_item[] item = {new post_item()};

    Tab1Presenter tab1Presenter;
    View view;



    void initView(){

        post_list= (ListView) view.findViewById(R.id.post_list);
        push_list= (ListView) view.findViewById(R.id.push_list);

        push_adapter =new pushAdapter();
        post_adapter = new postAdapter();

        post_list.setAdapter(post_adapter);
        push_list.setAdapter(push_adapter);

        push_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.transaction = getActivity().getSupportFragmentManager().beginTransaction();

                MainActivity.transaction.replace(R.id.content, Preview.newInstance(push_adapter.getidx(position)));
                MainActivity.transaction.addToBackStack(null);
                MainActivity.transaction.commit();
            }
        });

        post_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item[0] = (com.example.jin2.myapplication.tab1.adapter.post.post_item) post_adapter.getItem(position);
                System.out.println("눌림 -->"+item[0].getUrl());

                MainActivity.transaction = getActivity().getSupportFragmentManager().beginTransaction();

                MainActivity.transaction.replace(R.id.content, post_link.newInstance(item[position].getUrl()));
                MainActivity.transaction.addToBackStack(null);
                MainActivity.transaction.commit();
            }
        });

    }

    public static Tab1 newInstance() {
        Tab1 fragment = new Tab1();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.tab1_lay,container,false);
        initView();


        tab1Presenter = new Tab1Presenter();
        tab1Presenter.attachView(this);
        tab1Presenter.setPostAdapterView(post_adapter);
        tab1Presenter.setPostAdapterModel(post_adapter);
        tab1Presenter.setPostModel(postData.getInstance());

        tab1Presenter.loadPost("gawooid0");

        tab1Presenter.setPushAdapterModel(push_adapter);
        tab1Presenter.setPushAdapterView(push_adapter);
        tab1Presenter.setPushModel(pushData.getInstance());

        tab1Presenter.loadPush("gawooid0");

        return view;
    }//oncreateview



//    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
//        @Override
//        public void run(boolean success) {
//            if (success) {
//                // 네이버 아이디로 로그인 인증이 성공했을 때 수행할 코드 추가
////
////                RequestApiTask requestApiTask = new RequestApiTask();
////                requestApiTask.execute();
//                Toast.makeText(getContext(),"이미 아이디가 등록 되어있습니다.",Toast.LENGTH_LONG);
//                MainActivity.naverCheck=true;
//            } else {
//                String errorCode = OAuthLogin.getInstance()
//                        .getLastErrorCode(getContext()).getCode();
//                String errorDesc = OAuthLogin.getInstance().getLastErrorDesc(getContext());
//                Toast.makeText(getContext(), "errorCode:" + errorCode
//                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
//                MainActivity.naverCheck=false;
//            }
//
//
//        };
//    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}