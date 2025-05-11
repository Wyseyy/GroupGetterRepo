package com.example.groupgetter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

//Interface for defining the API endpoints for a users registration and login.
public interface Service {
    @POST("/register")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> register(@Body RegRequest request);

    @POST("/login")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> login(@Body LoginRequest request);
}