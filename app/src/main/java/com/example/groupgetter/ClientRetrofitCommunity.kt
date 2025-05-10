package com.example.groupgetter

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientRetrofitCommunity {
    private const val BASE_URL = "http://10.0.2.2:3000"

    val api: CommunityApi by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retrofit.create(CommunityApi::class.java)
    }

}