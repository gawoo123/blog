package com.example.jin2.myapplication.tab1;

/**
 * Created by jin on 2017-11-15.
 */

public interface Tab1Contract {

    interface View{

    }

    interface  Presenter{

        void attachView(View view);

        void dettachView();

        void loadPost();

        void loadPush();
    }
}
