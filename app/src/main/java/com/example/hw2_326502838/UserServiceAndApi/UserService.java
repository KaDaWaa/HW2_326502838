package com.example.hw2_326502838.UserServiceAndApi;

import com.example.hw2_326502838.user.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("/api")
    public Call<Root> getUsers(
            @Query("limit") Integer limit,
            @Query("skip") Integer skip,
            @Query("select") String select
    );


}
