package com.example.jin2.myapplication.tab1;

/**
 * Created by jin on 2017-11-15.
 */

public class Tab1Presenter implements Tab1Contract.Presenter {

    Tab1Contract.View view;
    Tab1Model tab1Model;

    public Tab1Presenter(Tab1Contract.View view) {
        this.view = view;
        this.tab1Model = new Tab1Model();
    }






    @Override
    public void attachView(Tab1Contract.View view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
        view = null;
    }

    @Override
    public void loadPost() {
        tab1Model.getPost();

    }

    @Override
    public void loadPush() {
        tab1Model.getPost();

    }


}
