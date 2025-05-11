package com.example.groupgetter

import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//An object that both builds and returns a Retrofit client for Reddit OAuth2 authentication
object AuthoriseApiClient {
    private const val BASE_URL = "https://www.reddit.com/api/v1/"

    fun create(): AuthoriseApi{
        val credentials = Credentials.basic("","")

        val client = OkHttpClient.Builder().addInterceptor{ chain ->
            val request = chain.request().newBuilder()
                    .header("Authorization", credentials)
                    .header("User-Agent", "GroupGetter/0.1 by Wysey")
                    .build()
            chain.proceed(request)
        }
                .build()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthoriseApi::class.java)
    }
}