package com.example.jin2.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jin2 on 2017-10-18.
 */

public class main_menu extends Fragment {


    @BindView(R.id.push_list)
    ListView push_list;
    @BindView(R.id.post_list)
    ListView post_list;

    public static main_menu newInstance() {
        main_menu fragment = new main_menu();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RetroService gitHubService = RetroService.retrofit.create(RetroService.class);
        Call<Contributor> call = gitHubService.postContributors("id0");
        System.out.println("call ->>>> "+call.request().url());
        call.enqueue(new Callback<Contributor>() {
            @Override
            public void onResponse(Call<Contributor> call,
                                   Response<Contributor> response) {

                System.out.println("api res ->>>> " + response.body());

                System.out.println("api res1 ->>>> " + response.body().getPush().get(0).getIdx());
                System.out.println("api res 2->>>> " + response.body().getPush().get(0).getDate());


                System.out.println("api res1 ->>>> " + response.body().getPost().get(0).getDate());
                System.out.println("api res 2->>>> " + response.body().getPost().get(0).getUrl());

            }
            @Override
            public void onFailure(Call<Contributor> call, Throwable t) {
                System.out.println("fail!!");
                t.printStackTrace();

            }
        });





    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_main,container,false);
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