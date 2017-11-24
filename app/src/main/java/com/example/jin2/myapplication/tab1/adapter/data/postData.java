package com.example.jin2.myapplication.tab1.adapter.data;

import com.example.jin2.myapplication.retrofit.Contributor;
import com.example.jin2.myapplication.retrofit.RetrofitHelper;
import com.example.jin2.myapplication.tab1.Tab1Presenter;
import com.example.jin2.myapplication.tab1.adapter.post.post_item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jin on 2017-11-17.
 */

public class postData {

    static postData post_data;
    static ArrayList<post_item> post_items;



    public static postData getInstance() {
        if(post_data==null){
            post_data = new postData();
        }
        return post_data;
    }

    public ArrayList<post_item> getPostData(String id) {

        post_items= new ArrayList<>();

        Call<List<Contributor>> call = RetrofitHelper.getInstance().CreateBaseApi().postContributors(id);
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call,
                                   Response<List<Contributor>> response) {

                for (int i = 0; i < response.body().size(); i++) {
                    post_item post_item = new post_item();
                    post_item.setDate(response.body().get(i).getDate());
                    post_item.setUrl(response.body().get(i).getPosting_url());
                    post_items.add(post_item);
                }
                Tab1Presenter.postView.notifyAdapter();
         }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                System.out.println("tab1 -> fail!!");

                t.printStackTrace();
            }
        });

        return post_items;

    }
}
