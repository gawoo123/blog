package com.example.jin2.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jin2.myapplication.retrofit.RetroService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

     Fragment fragment;
    FragmentManager fragmentManager;

    String url = "52.78.156.24/blog_api/index.php";

   public static FragmentTransaction transaction;

   static boolean naverCheck;
    static SharedPreferences pref;
    static String myId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        fragmentManager = getSupportFragmentManager();

//        pref = getSharedPreferences("login", Activity.MODE_PRIVATE);
//        myId = pref.getString("id","");
        System.out.println("my id --> "+myId);



        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                selectedFragment = Tab1.newInstance();
                                break;
                            case R.id.navigation_dashboard:
                                selectedFragment = Tab2.newInstance();
                                break;
                            case R.id.navigation_notifications:
                                selectedFragment = Tab3.newInstance();
                                break;
                        }
                         transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only


//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment selectedFragment = null;
//                switch (item.getItemId()) {
//                    case R.id.navigation_home:
//                        selectedFragment = test.newInstance();
//                        return true;
//                    case R.id.navigation_dashboard:
//                        selectedFragment = test.newInstance();
//                        return true;
//                    case R.id.navigation_notifications:
//                        Toast.makeText(MainActivity.this, "333333333333d", Toast.LENGTH_LONG).show();
//                        return true;
//                }
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.container,selectedFragment);
//                transaction.commit();
//                return true;
//            }
//        });
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.container, test.newInstance());
//        transaction.commit();


    }

    @Override
    protected void onResume() {

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, Tab1.newInstance());
        transaction.commit();


        super.onResume();
    }
}
