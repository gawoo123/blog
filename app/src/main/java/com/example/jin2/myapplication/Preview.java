package com.example.jin2.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

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



//    public void posting(){
//        new Thread(){
//            @Override
//            public void run() {
//                String token = "sxPr_VcIM7gMVARPEVCR";//애플리케이션 클라이언트 아이디값";//
//                String header = "Bearer " + mOAuthLoginModule.getAccessToken(getContext()); // Bearer 다음에 공백 추가
//                try {
//                    // api url 설정
//                    String apiURL = "https://openapi.naver.com/blog/writePost.json";
//                    MultipartUtil mu = new MultipartUtil(apiURL);
//                    // 접큰 토큰 헤더 추가
//                    mu.addHeaderField("Authorization", header);
//                    mu.readyToConnect();
//                    // blog 글쓰기 필수 요청변수 title 추가
//                    String title = "네이버 multi-part 이미지 첨부 테스트";
//                    // blog 글쓰기 필수 요청변수 contents - 첨부이미지는 <img src='#0' />, <img src='#1' /> ... 으로 참조 가능
//                    String contents = "<font color='red'>multi-part</font>로 첨부한 글입니다. <br>  이미지 첨부 <br> <img src='#0' />";
//                    mu.addFormField("title", title);
//                    mu.addFormField("contents", contents);
//
//                    // [시작] image 첨부 로직 - 필요시 이미지수 만큼 반복
////                            String test="http://52.78.156.24/img/a1.jpg";
//////                            System.out.println("왜 짤리니 "+header);
//                    File uploadFile1 = new File(Environment.getExternalStorageDirectory().toString()+"/test.jpg");
//                    mu.addFilePart("image", uploadFile1);
////                            File uploadFile2 = new File(Environment.getExternalStorageDirectory().toString()+"/downimage.PNG");
////                            mu.addFilePart("image", uploadFile2);
//                    // [종료] 이미지 첨부 로직 - 필요시 이미지수 만큼 반복
//
//                    // HTTP 호출 결과 수신
//                    List response = mu.finish();
//                    System.out.println("SERVER REPLIED:");
//                    String test = null;
//
//                    for (Object line : response) {
//                        test= String.valueOf(response);
//                    }
//                    JSONObject wrapObject = new JSONObject(test);
//
//                    // JSONObject 의 키 "list" 의 값들을 JSONArray 형태로 변환
//                    JSONArray jsonArray = new JSONArray(wrapObject.getString("list"));
//                    System.out.println("array : "+jsonArray);
//
//                } catch (Exception e) {
//                    System.out.println("part2:"+e.toString());
//                }
//            }
//        }.start();
//
//    }
}
