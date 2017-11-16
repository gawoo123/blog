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




    
    
}


