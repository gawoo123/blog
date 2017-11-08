package com.example.jin2.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by jin on 2017-11-02.
 */

public class post_link extends Fragment{

    static String url;
    public static post_link newInstance(String url_) {
        url=url_;
        post_link fragment = new post_link();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.link_post,container,false);
        WebView webView = (WebView) view.findViewById(R.id.main_web_view);
        webView.loadUrl(url);



        return view;
    }
}
