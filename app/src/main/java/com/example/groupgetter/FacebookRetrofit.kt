package com.example.groupgetter

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FacebookRetrofit {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("https://graph.facebook.com/v18.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    val api: FacebookApi by lazy {
        retrofit.create(FacebookApi::class.java)
    }
}