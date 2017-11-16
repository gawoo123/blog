package com.example.jin2.myapplication.tab1;

import com.example.jin2.myapplication.retrofit.Contributor;
import com.example.jin2.myapplication.retrofit.RetroService;
import com.example.jin2.myapplication.postAdapter;
import com.example.jin2.myapplication.post_item;
import com.example.jin2.myapplication.pushAdapter;
import com.example.jin2.myapplication.push_item;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jin on 2017-11-16.
 */

public class Tab1Model {

    postAdapter postAdapter;
    pushAdapter pushAdapter;
    RetroService gitHubService;
    Call<Contributor> call;


    ArrayList<post_item> post_items;
    ArrayList<push_item> push_items;

    postAdapter setPostAdapter() {
        postAdapter = new postAdapter();
        return postAdapter;
    }

    pushAdapter setPushAdapter() {
        pushAdapter = new pushAdapter();
        return pushAdapter;
    }

    void addPostItem(ArrayList<post_item> items) {
        post_items = items;
    }

    void addPushItem(ArrayList<push_item> items) {
        push_items = items;
    }

    void setGitHubService(String id) {
        gitHubService = RetroService.retrofit.create(RetroService.class);
        call = gitHubService.postContributors("gawooid0");
    }

    void getPost() {
        call.enqueue(new Callback<Contributor>() {
            @Override
            public void onResponse(Call<Contributor> call, Response<Contributor> response) {
                for(int i=0; i<response.body().getPost().size();i++){
                    postAdapter.addItem(response.body().getPost().get(i).getDate(),response.body().getPost().get(i).getUrl());
                    notifyPost();
                }
            }
            @Override
            public void onFailure(Call<Contributor> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    void getPush() {
        call.enqueue(new Callback<Contributor>() {
            @Override
            public void onResponse(Call<Contributor> call, Response<Contributor> response) {
                for(int i=0; i<response.body().getPush().size();i++){
                    pushAdapter.addItem(response.body().getPush().get(i).getDate(),response.body().getPush().get(i).getIdx());
                    notifyPush();
                }
            }

            @Override
            public void onFailure(Call<Contributor> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    void notifyPost(){
        postAdapter.notifyDataSetChanged();
    }

    void notifyPush() {
        pushAdapter.notifyDataSetChanged();
    }




}








