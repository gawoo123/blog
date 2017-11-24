package com.example.jin2.myapplication.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jin2 on 2017-10-19.
 */

public class Contributor {


    public List<Post> getPost() {
        return post;
    }
    public List<Push> getPush() {
        return push;
    }

    @SerializedName("post")
    List<Post> post  = null;

    @SerializedName("push")
    List<Push> push = null;


    public String getRes() {
        return res;
    }
    public String getPosting_url() {
        return posting_url;
    }

    String res;
    String posting_url;
    String id;
    String cus_id;
    String date;
    String category_idx;
    String post_idx;

    public String getPost_idx() {
        return post_idx;
    }

    public void setPost_idx(String post_idx) {
        this.post_idx = post_idx;
    }
    //    List<String> push;
//    List<String> post;

    public String getCus_id() {
        return cus_id;
    }

    public void setCus_id(String cus_id) {
        this.cus_id = cus_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory_idx() {
        return category_idx;
    }

    public void setCategory_idx(String category_idx) {
        this.category_idx = category_idx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public class Post{
        @SerializedName("posting_url") String url;
        @SerializedName("date") String date;

        public String getUrl() {
            return url;
        }

        public String getDate() {
            return date;
        }
    }
    public class Push{
        @SerializedName("category_idx") String idx;
        @SerializedName("date") String date;

        public String getIdx() {
            return idx;
        }

        public String getDate() {
            return date;
        }
    }
//    @Override
//    public String toString() {
//        return id+"/"+"/"+res;
//    }


}