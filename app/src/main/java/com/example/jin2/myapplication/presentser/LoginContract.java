package com.example.jin2.myapplication.presentser;

import android.view.View;

import butterknife.OnItemClick;

/**
 * Created by jin on 2017-11-15.
 */

public interface LoginContract {

    interface View {

    }

    interface Presenter {

        void attachView(View view);

        void detachView();

    }
}
