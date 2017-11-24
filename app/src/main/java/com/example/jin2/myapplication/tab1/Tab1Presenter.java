package com.example.jin2.myapplication.tab1;

import com.example.jin2.myapplication.tab1.adapter.data.postData;
import com.example.jin2.myapplication.tab1.adapter.data.pushData;
import com.example.jin2.myapplication.tab1.adapter.post.postContract;
import com.example.jin2.myapplication.tab1.adapter.push.pushContract;

/**
 * Created by jin on 2017-11-15.
 */

public class Tab1Presenter implements Tab1Contract.Presenter {

    Tab1Contract.View view;

    public static postContract.View postView;
    postContract.Model post_modelView;

    postData postdata;
    pushData pushdata;


    public static pushContract.View pushView;
    pushContract.Model push_modelView;
//    pushData pushdata;


    @Override
    public void attachView(Tab1Contract.View view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
        view = null;
    }

    @Override
    public void loadPost(String id) {
        post_modelView.addItems(postdata.getPostData(id));
    }

    @Override
    public void loadPush(String id) {
        push_modelView.addItems(pushdata.getPushData(id));
    }

    @Override
    public void setPostAdapterView(postContract.View view) {
        this.postView = view;
    }

    @Override
    public void setPostAdapterModel(postContract.Model model) {
        this.post_modelView = model;
    }

    @Override
    public void setPostModel(postData postdata) {
        this.postdata = postdata;
    }

    @Override
    public void setPushAdapterView(pushContract.View view) {
        this.pushView=view;
    }

    @Override
    public void setPushAdapterModel(pushContract.Model model) {
        this.push_modelView=model;
    }

    @Override
    public void setPushModel(pushData pushdata) {
        this.pushdata=pushdata;
    }

}
