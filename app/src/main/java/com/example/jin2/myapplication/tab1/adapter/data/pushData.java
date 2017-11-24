package com.example.jin2.myapplication.tab1.adapter.data;

import com.example.jin2.myapplication.retrofit.Contributor;
import com.example.jin2.myapplication.retrofit.RetrofitHelper;
import com.example.jin2.myapplication.tab1.Tab1Presenter;
import com.example.jin2.myapplication.tab1.adapter.push.push_item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jin on 2017-11-20.
 */

public class pushData {

    static pushData push_data;
    public static ArrayList<push_item> push_items;


    public static pushData getInstance() {
        if(push_data==null){
            push_data = new pushData();
        }
        return push_data;
    }

    public ArrayList<push_item> getPushData(String id) {

        push_items= new ArrayList<>();

        Call<List<Contributor>> call = RetrofitHelper.getInstance().CreateBaseApi().pushContributors(id);
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call,
                                   Response<List<Contributor>> response) {


                for (int i = 0; i < response.body().size(); i++) {

                    push_item push_item = new push_item();
                    push_item.setDate(response.body().get(i).getDate());
                    push_item.setIdx(response.body().get(i).getCategory_idx());
                    push_item.setUrl(response.body().get(i).getPosting_url());
                    push_items.add(push_item);
                }
                Tab1Presenter.pushView.notifyAdapter();

            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                System.out.println("tab1 -> fail!!");
                t.printStackTrace();
            }
        });

        return push_items;
    }

}
