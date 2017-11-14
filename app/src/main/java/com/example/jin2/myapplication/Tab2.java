package com.example.jin2.myapplication;

/**
 * Created by jin2 on 2017-10-18.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;

import android.widget.Toast;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by jin2 on 2017-10-18.
 */

public class Tab2 extends Fragment {//

    public static Tab2 newInstance() {
        Tab2 fragment = new Tab2();
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

        View view = inflater.inflate(R.layout.tab2_lay,container,false);

        Button button = (Button) view.findViewById(R.id.naverLogout_btn);
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
//        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);

         mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                getActivity()
                ,"sxPr_VcIM7gMVARPEVCR"
                ,"U6_eKtizFx"
                ,"gawoo"
                //,OAUTH_CALLBACK_INTENT
                // SDK 4.1.4 버전부터는 OAUTH_CALLBACK_INTENT변수를 사용하지 않습니다.
        );


        Button btnSave = (Button) view.findViewById(R.id.button2);
        Button.OnClickListener btnSaveOnClickListener =
                new Button.OnClickListener() {

                    public void onClick(View arg0) {
                        OutputStream outStream = null;
                        String extStorageDirectory =
                                Environment.getExternalStorageDirectory().toString();

                        File file = new File(extStorageDirectory,        "test.jpg");
                        try {
                            outStream = new FileOutputStream(file);
//                            mSaveBm.compress(
//                                    Bitmap.CompressFormat.PNG, 100, outStream);
//                            outStream.flush();
//                            outStream.close();

                            System.out.println("경로 "+ extStorageDirectory);

                            Toast.makeText(getActivity(),
                                    "Saved", Toast.LENGTH_LONG).show();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),
                                    e.toString(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),
                                    e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                };

        btnSave.setOnClickListener(btnSaveOnClickListener);




        return view;


    }

//    @OnClick(R.id.posting_btn)
//    void postClicked(){
//
//        Toast.makeText(getContext(),"@@@@",Toast.LENGTH_SHORT);
//        mOAuthLoginModule.requestApi(getContext(), accessToken, "http://52.78.156.24/blog_api/index.php");
//    }





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
//            OAuthLogin mOAuthLoginInstance=null;
//            String at = mOAuthLoginInstance.getAccessToken(getActivity());
            System.out.println("check token"+at);
            return mOAuthLoginModule.requestApi(getActivity(), at, url_);
        }

        protected void onPostExecute(String content) {
            System.out.println("api res:" +content);

        }
    }

    public void posting(){
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
                    mu.addFormField("title", title);
                    mu.addFormField("contents", contents);

                    // [시작] image 첨부 로직 - 필요시 이미지수 만큼 반복
//                            String test="http://52.78.156.24/img/a1.jpg";
////                            System.out.println("왜 짤리니 "+header);
                    File uploadFile1 = new File(Environment.getExternalStorageDirectory().toString()+"/test.jpg");
                    mu.addFilePart("image", uploadFile1);
//                            File uploadFile2 = new File(Environment.getExternalStorageDirectory().toString()+"/downimage.PNG");
//                            mu.addFilePart("image", uploadFile2);
                    // [종료] 이미지 첨부 로직 - 필요시 이미지수 만큼 반복

                    // HTTP 호출 결과 수신
                    List response = mu.finish();
                    System.out.println("SERVER REPLIED:");
                    String test = null;

                    for (Object line : response) {
                        test= String.valueOf(response);
                    }
                    JSONObject wrapObject = new JSONObject(test);

                    // JSONObject 의 키 "list" 의 값들을 JSONArray 형태로 변환
                    JSONArray jsonArray = new JSONArray(wrapObject.getString("list"));
                    System.out.println("array : "+jsonArray);

                } catch (Exception e) {
                    System.out.println("part2:"+e.toString());
                }
            }
        }.start();

    }


    
    
}


