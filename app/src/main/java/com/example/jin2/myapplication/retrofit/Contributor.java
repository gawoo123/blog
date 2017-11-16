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

    @SerializedName("post_list")
    List<Post> post  = null;

    @SerializedName("push_list")
    List<Push> push = null;


    public String getRes() {
        return res;
    }

    public String getUrl() {
        return url;
    }

    String res;
    String url;


    public class Post{
        @SerializedName("url") String url;
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
    @Override
    public String toString() {
        return post+"/"+"/"+getUrl();
    }


}