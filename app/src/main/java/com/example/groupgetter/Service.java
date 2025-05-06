package com.example.groupgetter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Service {
    @POST("/register")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> register(@Body RegRequest request);

    @POST("/login")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> login(@Body LoginRequest request);
}