package com.example.jin2.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jin2.myapplication.tab1.Tab1Contract;
import com.example.jin2.myapplication.tab1.Tab1Presenter;

import java.util.List;

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


    final com.example.jin2.myapplication.post_item[] item = {new post_item()};

    Tab1Presenter tab1Presenter;
    View view;



    void initView(){

        post_list= (ListView) view.findViewById(R.id.post_list);
        push_list= (ListView) view.findViewById(R.id.push_list);
        push_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.transaction = getActivity().getSupportFragmentManager().beginTransaction();

                MainActivity.transaction.replace(R.id.content,Preview.newInstance(item[0].getUrl()));
                MainActivity.transaction.addToBackStack(null);
                MainActivity.transaction.commit();
            }
        });

        post_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item[0] = (com.example.jin2.myapplication.post_item) post_adapter.getItem(position);
                System.out.println("눌림 -->"+item[0].getUrl());

                MainActivity.transaction = getActivity().getSupportFragmentManager().beginTransaction();

                MainActivity.transaction.replace(R.id.content,post_link.newInstance(item[0].getUrl()));
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
        tab1Presenter = new Tab1Presenter(this);

                                                    //        push_adapter = new pushAdapter();
                                                    //        post_adapter = new postAdapter();
                                                    //
                                                    //        push_list.setAdapter(push_adapter);
                                                    //        post_list.setAdapter(post_adapter);

                                                            // 리스트뷰 + 어뎁터

                                                    //        RetroService gitHubService = RetroService.retrofit.create(RetroService.class);
                                                    //        // 객체선언
                                                    //
                                                    //
                                                    //
                                                    //
                                                    //        Call<Contributor> call = gitHubService.listContribuotrs("id0");
                                                    //        System.out.println("call ->>>> "+call.request().url());
                                                    //        call.enqueue(new Callback<Contributor>() {
                                                    //            @Override
                                                    //            public void onResponse(Call<Contributor> call,
                                                    //                                   Response<Contributor> response) {
                                                    //
                                                    //                for(int i=0; i<response.body().getPush().size();i++){
                                                    //                    push_adapter.addItem(response.body().getPush().get(i).getDate(),response.body().getPush().get(i).getIdx());
                                                    //                }
                                                    //                for(int i=0; i<response.body().getPost().size();i++){
                                                    //                    post_adapter.addItem(response.body().getPost().get(i).getDate(),response.body().getPost().get(i).getUrl());
                                                    //                }
                                                    //                post_adapter.notifyDataSetChanged();
                                                    //                push_adapter.notifyDataSetChanged();
                                                    //            }
                                                    //            @Override
                                                    //            public void onFailure(Call<Contributor> call, Throwable t) {
                                                    //                System.out.println("fail!!");
                                                    //                t.printStackTrace();
                                                    //            }
                                                    //        });
                                                    //        // Retrofit으로 리스트 받아와서 뿌려주는 부분

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