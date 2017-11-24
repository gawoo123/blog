package com.example.jin2.myapplication;

/**
 * Created by jin2 on 2017-10-18.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import com.example.jin2.myapplication.retrofit.Contributor;
import com.example.jin2.myapplication.retrofit.RetroService;
import com.example.jin2.myapplication.presentser.LoginPresnter;
import com.example.jin2.myapplication.retrofit.RetrofitHelper;

public class Login extends AppCompatActivity {

    EditText idInput, passwordInput;
    CheckBox autoLogin;
    Boolean loginChecked=false;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Button login_btn;
    Button sign_btn;
    String id;
    String pw;

    LoginPresnter loginPresnter;




    public static RetroService gitRetroService;
//    Call<Contributor> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initView();

    }

    void initView(){

        idInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        autoLogin = (CheckBox) findViewById(R.id.checkBox);
        login_btn = (Button) findViewById(R.id.loginButton);
        sign_btn = (Button) findViewById(R.id.signupButton);
        idInput.setText("gawooid0");
        passwordInput.setText("gawoopw0");

//        gitRetroService = RetroService.retrofit.create(RetroService.class);

        RetrofitHelper.getInstance().init();







        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id= String.valueOf(idInput.getText());
                pw= String.valueOf(passwordInput.getText());

                MainActivity.myId=id;

                Call<Contributor> call = RetrofitHelper.getInstance().CreateBaseApi().loginContributors("gawooid0", "gawoopw0");
                System.out.println("call ->>>> "+call.request().url());
                call.enqueue(new Callback<Contributor>() {
                    @Override
                    public void onResponse(Call<Contributor> call,
                                           Response<Contributor> response) {

                        System.out.println("@api res getId->>>> " + response.body().getId());
                        System.out.println("@api res getRes ->>>> " + response.body().getRes());
//                        System.out.println("api res  error ->>>> " + response.errorBody());
                        if (response.body().getRes().equals("1") && response.body().getId().equals(id)) {
                            System.out.println("...?");
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            System.out.println("실패");
                            Toast.makeText(Login.this, "로그인 실패", Toast.LENGTH_LONG);
                        }
                    }
                    @Override
                    public void onFailure(Call<Contributor> call, Throwable t) {
                        System.out.println("fail!!");
                        t.printStackTrace();
                    }
                });
            }
        }); // 로그인 버튼 클릭이벤트


        pref = getSharedPreferences("login", Activity.MODE_PRIVATE);

        // if autoLogin checked, get input
        if (pref.getBoolean("autoLogin", false)) {
            idInput.setText(pref.getString("id", ""));
            passwordInput.setText(pref.getString("pw", ""));
            autoLogin.setChecked(true);
            // goto mainActivity
        } else {
            // if autoLogin unChecked
            String id = idInput.getText().toString();
            String password = passwordInput.getText().toString();
            Boolean validation = loginValidation(id, password);

            if(validation) {
                Toast.makeText(Login.this, "Login Success", Toast.LENGTH_LONG).show();
                // save id, password to Database
                if(loginChecked) {
                    // if autoLogin Checked, save values
                    editor.putString("id", id);
                    editor.putString("pw", password);
                    editor.putBoolean("autoLogin", true);
                    editor.commit();

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
                // goto mainActivity

            } else {
                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();
                // goto LoginActivity
            }
        }

        // set checkBoxListener
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    loginChecked = true;
                } else {
                    // if unChecked, removeAll
                    loginChecked = false;
                    editor.clear();
                    editor.commit();
                }
            }
        });
    }

    private boolean loginValidation(String id, String password) {
        if(pref.getString("id","").equals(id) && pref.getString("pw","").equals(password)) {
            // login success
            return true;
        } else if (pref.getString("id","").equals(null)){
            // sign in first
            Toast.makeText(Login.this, "Please Sign in first", Toast.LENGTH_LONG).show();
            return false;
        } else {
            // login failed
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresnter.detachView();
    }
}
