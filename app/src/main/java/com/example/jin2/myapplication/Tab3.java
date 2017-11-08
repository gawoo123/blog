package com.example.jin2.myapplication;

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



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.tab3_lay,container,false);


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

        final TextView textView = (TextView) view.findViewById(R.id.textView7);

        Button btn = (Button) view.findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg  = String.valueOf(mOAuthLoginModule.getState(getContext()));
//                textView.setText(msg);
                if(msg.equals("NEED_LOGIN")){
                    textView.setText("ㄴㄴㄴㄴㄴㄴ");
                }else if(msg.equals("OK")){
                    textView.setText("ㅇㅇㅇㅇ");
                }else{
                    textView.setText("에러");
                }

            }
        });


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
