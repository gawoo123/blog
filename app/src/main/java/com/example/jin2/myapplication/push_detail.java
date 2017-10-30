package com.example.jin2.myapplication;

/**
 * Created by jin2 on 2017-10-18.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;


import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jin2 on 2017-10-18.
 */

public class push_detail extends Fragment {//

    public static push_detail newInstance() {
        push_detail fragment = new push_detail();
        return fragment;
    }


    @BindView(R.id.title)
    TextView titleView;
    @BindView(R.id.content)
    TextView contentView;
//    @BindView(R.id.button)
//    Button postView;
    @BindView(R.id.cancel_btn)
    Button cancelView;

    OAuthLogin mOAuthLoginModule;

    String accessToken;
    String tokenType;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        View view = inflater.inflate(R.layout.blog_write,container,false);

        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent =new Intent(getContext(),API.class);
//                startActivity(intent);

                Toast.makeText(getContext(),"@@@@",Toast.LENGTH_SHORT);
//
//                RequestApiTask requestApiTask = new RequestApiTask();
//                requestApiTask.execute();



//                OAuthLogin.getInstance().startOauthLoginActivity(getActivity(), mOAuthLoginHandler);
                new Thread(){
                    @Override
                    public void run() {
                        OAuthLogin.getInstance().logoutAndDeleteToken(getContext());

                        mOAuthLoginModule.requestApi(getContext(), accessToken, "http://52.78.156.24/blog_api/index.php");

                    }
                }.start();
            }
        });


        OAuthLoginButton mOAuthLoginButton = (OAuthLoginButton) view.findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);

         mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                getActivity()
                ,"sxPr_VcIM7gMVARPEVCR"
                ,"U6_eKtizFx"
                ,"gawoo"
                //,OAUTH_CALLBACK_INTENT
                // SDK 4.1.4 버전부터는 OAUTH_CALLBACK_INTENT변수를 사용하지 않습니다.
        );

        
        
        return view;


    }

//    @OnClick(R.id.posting_btn)
//    void postClicked(){
//
//        Toast.makeText(getContext(),"@@@@",Toast.LENGTH_SHORT);
//        mOAuthLoginModule.requestApi(getContext(), accessToken, "http://52.78.156.24/blog_api/index.php");
//    }

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                // 네이버 아이디로 로그인 인증이 성공했을 때 수행할 코드 추가
//
//                RequestApiTask requestApiTask = new RequestApiTask();
//                requestApiTask.execute();

                //
//                RequestApiTask requestApiTask = new RequestApiTask();
//                requestApiTask.execute();

                                new Thread(){
                    @Override
                    public void run() {

                        String token = "sxPr_VcIM7gMVARPEVCR";//애플리케이션 클라이언트 아이디값";//
                        String header = "Bearer " + mOAuthLoginModule.getAccessToken(getContext()); // Bearer 다음에 공백 추가
                        try {
                            // api url 설정
                            String apiURL = "https://openapi.naver.com/blog/writePost.json";
                            MultipartUtil mu = new MultipartUtil(apiURL);
                            // 접큰 토큰 헤더 추가
                            mu.addHeaderField("Authorization", header);
                            mu.readyToConnect();
                            // blog 글쓰기 필수 요청변수 title 추가
                            String title = "네이버 multi-part 이미지 첨부 테스트";
                            // blog 글쓰기 필수 요청변수 contents - 첨부이미지는 <img src='#0' />, <img src='#1' /> ... 으로 참조 가능
                            String contents = "<font color='red'>multi-part</font>로 첨부한 글입니다. <br>  이미지 첨부 <br> <img src='#0' />";
                            contents=contents+"<img src='http://52.78.156.24/img/a1.jpg'>";

                            mu.addFormField("title", title);
                            mu.addFormField("contents", contents);

                            // [시작] image 첨부 로직 - 필요시 이미지수 만큼 반복
//                            String test="http://52.78.156.24/img/a1.jpg";
////                            System.out.println("왜 짤리니 "+header);
//                            File uploadFile1 = new File(test);
//                            mu.addFilePart("image", uploadFile1);
                            // [종료] 이미지 첨부 로직 - 필요시 이미지수 만큼 반복

                            // HTTP 호출 결과 수신
                            List response = mu.finish();
                            System.out.println("SERVER REPLIED:");
                            String test = null;

                            for (Object line : response) {
                                test= String.valueOf(response);
                                System.out.println("가라"+test);
                                System.out.println("part1:"+line);
                            }
//                            JSONArray jsonArray = new JSONArray(test);
//                            JSONObject jsonObject = new JSONObject();
//                            JSONArray jsonArray = new JSONArray();


                            System.out.println("json before--> "+test);

                            JSONObject wrapObject = new JSONObject(test);

        // JSONObject 의 키 "list" 의 값들을 JSONArray 형태로 변환
                            JSONArray jsonArray = new JSONArray(wrapObject.getString("list"));
                            System.out.println("array : "+jsonArray);



                        } catch (Exception e) {
                            System.out.println("part2:"+e.toString());
                        }
                    }
                }.start();


            } else {
                String errorCode = OAuthLogin.getInstance()
                        .getLastErrorCode(getContext()).getCode();
                String errorDesc = OAuthLogin.getInstance().getLastErrorDesc(getContext());
                Toast.makeText(getContext(), "errorCode:" + errorCode
                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }


        };
    };




     class RequestApiTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
//            mApiResultText.setText((String) "");
        }

        @Override
        protected String doInBackground(Void... params) {
//            String url = "https://apis.naver.com/nidlogin/nid/getHashId_v2.xml";

            String url_ = "https://openapi.naver.com/blog/writePost.json";
//            String at = OAuthLogin.getInstance().getAccessToken(getActivity());
           String at =  mOAuthLoginModule.getAccessToken(getContext());
//            Object mOAuthLoginInstance = OAuthLogin.getInstance();


//

//            OAuthLogin mOAuthLoginInstance=null;
//            String at = mOAuthLoginInstance.getAccessToken(getActivity());
            System.out.println("check token"+at);
            return mOAuthLoginModule.requestApi(getActivity(), at, url_);
        }

        protected void onPostExecute(String content) {
            System.out.println("api res:" +content);

        }
    }


    
    
}


