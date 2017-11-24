package com.example.jin2.myapplication.post_manage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.jin2.myapplication.MainActivity;
import com.example.jin2.myapplication.R;

/**
 * Created by jin on 2017-11-02.
 */

public class post_link extends Fragment{

    static String url;
    WebView webView;
    public static post_link newInstance(String url_) {
        url=url_;
        post_link fragment = new post_link();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.link_post,container,false);
        webView = (WebView) view.findViewById(R.id.main_web_view);
        webView.loadUrl(url);

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                System.out.println("event "+keyCode);

                //This is the filter
                if (event.getAction()!=KeyEvent.ACTION_DOWN)
                    return true;

                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (webView.canGoBack()) {
                        webView.goBack();
                        System.out.println("1");

                    } else {

                        System.out.println("2");
                        ((MainActivity)getActivity()).onBackPressed();
                    }

                    return true;
                }

                return false;
            }
        });




        return view;
    }

}
