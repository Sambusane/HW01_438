package com.example.hw01_438;



import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderAPI {

    @GET("posts")
    retrofit2.Call<List<Post>> getPosts(@Query("userId") int postId);
}
