package com.example.jin2.myapplication.retrofit;

import android.annotation.SuppressLint;
import android.util.Log;

import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jin on 2017-11-20.
 */

public class RetrofitHelper {

    private static final String TAG = "RetrofitHelper";
    private static RetrofitHelper mInstance;
    private Retrofit mRetrofit;


    private RetrofitHelper() {
    }

    public static RetrofitHelper getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitHelper.class) {
                mInstance = new RetrofitHelper();
            }
        }
        return mInstance;
    }

    public void init() {
        if (mRetrofit != null) {
            Log.d(TAG, "Retrofit is already initialized");
        }
        mRetrofit = getDefaultRetrofit();
    }

    private Retrofit getDefaultRetrofit() {
        OkHttpClient httpClient = new OkHttpClient.Builder().build();

        try {
            SSLContext sc;
            sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new X509TrustManager() {
                @SuppressLint("TrustAllX509TrustManager")
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @SuppressLint("TrustAllX509TrustManager")
                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new SecureRandom());

            HostnameVerifier hv = (hostname, session) -> true;

            String workerClassName = "okhttp3.OkHttpClient";
            try {
                Class workerClass = Class.forName(workerClassName);
                Field hostnameVerifier = workerClass.getDeclaredField("hostnameVerifier");
                hostnameVerifier.setAccessible(true);
                hostnameVerifier.set(httpClient, hv);

                Field sslSocketFactory = workerClass.getDeclaredField("sslSocketFactory");
                sslSocketFactory.setAccessible(true);
                sslSocketFactory.set(httpClient, sc.getSocketFactory());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Retrofit.Builder()
                .baseUrl("https://13.114.222.179/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RetroService CreateBaseApi() {

        return mRetrofit.create(RetroService.class);
    }
}