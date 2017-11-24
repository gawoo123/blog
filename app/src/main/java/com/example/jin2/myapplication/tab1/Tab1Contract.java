package com.example.jin2.myapplication.tab1;

import com.example.jin2.myapplication.tab1.adapter.data.postData;
import com.example.jin2.myapplication.tab1.adapter.data.pushData;
import com.example.jin2.myapplication.tab1.adapter.post.postContract;
import com.example.jin2.myapplication.tab1.adapter.push.pushContract;

/**
 * Created by jin on 2017-11-15.
 */

public interface Tab1Contract {

    interface View{

    }

    interface  Presenter{

        void attachView(View view);

        void dettachView();

        void loadPost(String id);

        void loadPush(String id);

        void setPostAdapterView(postContract.View view);

        void setPostAdapterModel(postContract.Model model);

        void setPostModel(postData postdata);

        void setPushAdapterView(pushContract.View view);

        void setPushAdapterModel(pushContract.Model model);

        void setPushModel(pushData pushdata);


    }
}
