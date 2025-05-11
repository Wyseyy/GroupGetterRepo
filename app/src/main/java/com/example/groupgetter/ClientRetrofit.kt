package com.example.groupgetter


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//A class that provides a singleton retrofit instance with a base URL and
// JSON converter used to communicate with a backend (VS Code) specifically for Customer Service
object ClientRetrofit {
    private const val BASE_URL = "http://10.0.2.2:3000"

    val api: CServiceApi by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retrofit.create(CServiceApi::class.java)
    }
}