package com.example.groupgetter

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Object providing a Retrofit instance for making API calls to Tumblr,
// specifically for searching tagged posts.

object TumblrAuthoriseApiClient {
    private const val BASE_URL = "https://api.tumblr.com/"

    val instance: TumblrApi by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TumblrApi::class.java)
    }
}