package com.example.groupgetter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val authoriseApi: AuthoriseApi by lazy{
        retrofit.create(AuthoriseApi::class.java)
    }
}