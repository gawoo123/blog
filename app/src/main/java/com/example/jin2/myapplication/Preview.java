package com.example.jin2.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by jin on 2017-11-07.
 */

public class Preview extends Fragment {

    static String url;
    String test="setCompoundDrawablesWithIntrinsicBounds (int left, int top, int right, int bottom)\n" +
            ": 글씨의 왼쪽, 상단, 오른쪽, 아래에 이미지 추가 \n" +
            "\n" +
            "setCompoundDrawablePadding (int pad)\n" +
            ": 이미지와 글씨 사이의 간격 설정\n" +
            "\n" +
            "1\n" +
            "2\n" +
            "3\n" +
            "4\n" +
            "TextView txtEvent = (TextView)convertView.findViewById(R.id.cell_event);\n" +
            "txtEvent.setCompoundDrawablesWithIntrinsicBounds(R.drawable.event, 0, 0, 0);\n" +
            "txtEvent.setCompoundDrawablePadding(10);\n" +
            "txtEvent.setText(\"이벤트합니다.\");setCompoundDrawablesWithIntrinsicBounds (int left, int top, int right, int bottom)\n" +
            ": 글씨의 왼쪽, 상단, 오른쪽, 아래에 이미지 추가 \n" +
            "\n" +
            "setCompoundDrawablePadding (int pad)\n" +
            ": 이미지와 글씨 사이의 간격 설정\n" +
            "\n" +
            "1\n" +
            "2\n" +
            "3\n" +
            "4\n" +
            "TextView txtEvent = (TextView)convertView.findViewById(R.id.cell_event);\n" +
            "txtEvent.setCompoundDrawablesWithIntrinsicBounds(R.drawable.event, 0, 0, 0);\n" +
            "txtEvent.setCompoundDrawablePadding(10);\n" +
            "txtEvent.setText(\"이벤트합니다.\");";
    public static Preview newInstance(String url_) {
        url=url_;
        Preview fragment = new Preview();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.preview,container,false);

        /////////////

//        String longText = getResources().getString(R.string.long_text);


//        longText = " < img src='front_icon' >" + longText;



       Html.ImageGetter imgGetter = new Html.ImageGetter() {

            @Override
            public Drawable getDrawable(String source) {
                if (source.equals("icon")) {
                    Drawable drawable = getActivity().getResources().getDrawable(R.drawable.test2);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    return drawable;
                }

                return null;
            }
        };

        Spanned htmlText = Html.fromHtml( "test <img src=\"icon\" width=50 height=50> test", imgGetter, null );
        ///

        TextView content_tv = (TextView) view.findViewById(R.id.preview_content);
        content_tv.append(test);
//        content_tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.test2,0,0,0);
        content_tv.append(htmlText);
        content_tv.append(test);


        return view;
    }
}
