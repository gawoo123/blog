package com.example.jin2.myapplication;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

/**
 * Created by jin2 on 2017-10-18.
 */

public class Tab3 extends Fragment{

    public static Tab3 newInstance() {
        Tab3 fragment = new Tab3();
        return fragment;
    }

    int input;
    OAuthLogin mOAuthLoginModule;

    String accessToken;
    String tokenType;

    Button logout_btn;
    OAuthLoginButton mOAuthLoginButton;
    Handler handler;

    Button login_check_btn;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.tab3_lay,container,false);

        handler = new Handler();


        mOAuthLoginButton = (OAuthLoginButton) view.findViewById(R.id.buttonOAuthLoginImg);
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

       login_check_btn = (Button) view.findViewById(R.id.naverLogout_check);


        logout_btn = (Button) view.findViewById(R.id.naverLogout_btn);

        String login_check  = String.valueOf(mOAuthLoginModule.getState(getContext()));


        if(login_check.equals("NEED_LOGIN")){
            mOAuthLoginButton.setVisibility(View.VISIBLE);
            logout_btn.setVisibility(View.GONE);
            login_check_btn.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
        }else if(login_check.equals("OK")){
            mOAuthLoginButton.setVisibility(View.GONE);
            logout_btn.setVisibility(View.VISIBLE);

            login_check_btn.setBackgroundResource(android.R.drawable.button_onoff_indicator_on);
        }else{
            mOAuthLoginButton.setVisibility(View.VISIBLE);
            logout_btn.setVisibility(View.GONE);
            login_check_btn.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
            Toast.makeText(getContext(),"error : "+login_check,Toast.LENGTH_LONG);
        }


        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        OAuthLogin.getInstance().logoutAndDeleteToken(getContext());


                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mOAuthLoginButton.setVisibility(View.VISIBLE);
                                logout_btn.setVisibility(View.GONE);
                                login_check_btn.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
                            }
                        });


                    }
                }.start();
            }
        });

        Button btn = (Button) view.findViewById(R.id.button3);




//
//        Button btnSave = (Button) view.findViewById(R.id.button);
//        Button.OnClickListener btnSaveOnClickListener =
//                new Button.OnClickListener() {
//
//                    public void onClick(View arg0) {
//                        OutputStream outStream = null;
//                        String extStorageDirectory =
//                                Environment.getExternalStorageDirectory().toString();
//
//                        File file = new File(extStorageDirectory,        "test.jpg");
//                        try {
//                            outStream = new FileOutputStream(file);
////                            mSaveBm.compress(
////                                    Bitmap.CompressFormat.PNG, 100, outStream);
////                            outStream.flush();
////                            outStream.close();
//
//                            System.out.println("경로 "+ extStorageDirectory);
//
//                            Toast.makeText(getActivity(),
//                                    "Saved", Toast.LENGTH_LONG).show();
//
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                            Toast.makeText(getActivity(),
//                                    e.toString(), Toast.LENGTH_LONG).show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            Toast.makeText(getActivity(),
//                                    e.toString(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                };
//
//        btnSave.setOnClickListener(btnSaveOnClickListener);




        return view;

    }

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                // 네이버 아이디로 로그인 인증이 성공했을 때 수행할 코드 추가
//
//                RequestApiTask requestApiTask = new RequestApiTask();
//                requestApiTask.execute();
                Toast.makeText(getContext(),"이미 아이디가 등록 되어있습니다.",Toast.LENGTH_LONG);
                MainActivity.naverCheck=true;
                mOAuthLoginButton.setVisibility(View.GONE);
                logout_btn.setVisibility(View.VISIBLE);
                login_check_btn.setBackgroundResource(android.R.drawable.button_onoff_indicator_on);
            } else {
                String errorCode = OAuthLogin.getInstance()
                        .getLastErrorCode(getContext()).getCode();
                String errorDesc = OAuthLogin.getInstance().getLastErrorDesc(getContext());
                Toast.makeText(getContext(), "errorCode:" + errorCode
                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                MainActivity.naverCheck=false;
            }


        };
    };


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }



}
