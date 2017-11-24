package com.example.jin2.myapplication.retrofit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jin2 on 2017-10-19.
 */

public interface RetroService {


    long now = System.currentTimeMillis();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    String date = formatter.format(new Date());


    @GET("login/{id}/{pw}")
    Call<Contributor> loginContributors(
            @Path("id") String id
            , @Path("pw") String pw
    );
    @GET("pushs/{id}")
    Call<List<Contributor>> pushContributors(
            @Path("id") String id
    );
    @GET("posts/{id}")
    Call<List<Contributor>> postContributors(
            @Path("id") String id
    );
    @GET("posts/check/{id}")
    Call<List<Contributor>> checkContributors(
            @Path("id") String id
    );

}
