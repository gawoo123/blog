package com.example.jin2.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

     Fragment fragment;
    FragmentManager fragmentManager;

    String url = "52.78.156.24/blog_api/index.php";

   static FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        fragmentManager = getSupportFragmentManager();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                selectedFragment = main_menu.newInstance();
                                break;
                            case R.id.navigation_dashboard:
                                selectedFragment = push_detail.newInstance();
                                break;
                            case R.id.navigation_notifications:
                                selectedFragment = myInfo.newInstance();
                                break;
                        }
                         transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
         transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, test.newInstance());
        transaction.commit();



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


}
