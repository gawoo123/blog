package com.example.jin2.myapplication;

/**
 * Created by jin2 on 2017-10-18.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.http.AmazonHttpClient;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.google.firebase.iid.FirebaseInstanceId;

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        idInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        autoLogin = (CheckBox) findViewById(R.id.checkBox);
        login_btn = (Button) findViewById(R.id.loginButton);
        sign_btn = (Button) findViewById(R.id.signupButton);


        idInput.setText("id0");

        passwordInput.setText("pw0");

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id= String.valueOf(idInput.getText());
                pw= String.valueOf(passwordInput.getText());


                RetroService gitHubService = RetroService.retrofit.create(RetroService.class);
                Call<Contributor> call = gitHubService.loginContributors("id0", "pw0");
                System.out.println("call ->>>> "+call.request().url());
                call.enqueue(new Callback<Contributor>() {
                    @Override
                    public void onResponse(Call<Contributor> call,
                                           Response<Contributor> response) {

                        System.out.println("api res ->>>> " + response.body().toString());

                        if (response.body().toString() == "1") {


                        } else {
                            Toast.makeText(Login.this, "로그인 실패", Toast.LENGTH_LONG);
                        }
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<Contributor> call, Throwable t) {
                        System.out.println("fail!!");
                        t.printStackTrace();

                    }
                });
            }
        });


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


}
