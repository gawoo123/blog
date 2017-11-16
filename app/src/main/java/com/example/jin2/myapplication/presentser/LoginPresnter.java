package com.example.jin2.myapplication.presentser;

/**
 * Created by jin on 2017-11-15.
 */

public class LoginPresnter implements LoginContract.Presenter{

    LoginContract.View view;


    @Override
    public void attachView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
