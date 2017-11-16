package com.example.jin2.myapplication.retrofit;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jin2 on 2017-10-19.
 */

public interface RetroService {


    long now = System.currentTimeMillis();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    String date = formatter.format(new Date());

//    static Retrofit retrofit = null;


    @FormUrlEncoded
    @POST("login.php")
    Call<Contributor> loginContributors(
            @Field("id") String id
            , @Field("pw") String pwd
    );

//    @FormUrlEncoded
//    @POST("push_list.php")
//    Call<Contributor> pushContributors(
//            @Field("idx") String id
//    );
//
//    @FormUrlEncoded
//    @POST("post_list.php")
//    Call<Contributor> postContributors(
//            @Field("idx") String id
//    );

//    @FormUrlEncoded
//    @POST("mainList.php")
//    Call<Contributor> listContribuotrs(
//            @Field("id") String id
//    );

    @GET("pushs/{id}")
    Call<Contributor> pushContributors(
            @Path("id") String id
    );

    @GET("posts/{id}")
    Call<Contributor> postContributors(
            @Field("id") String id
    );



    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://13.125.8.11/app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();




//     @RequiresApi(api = Build.VERSION_CODES.N)
//     static Retrofit getClient() {
//        if (retrofit==null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl("http://13.125.8.11/app/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }

}
