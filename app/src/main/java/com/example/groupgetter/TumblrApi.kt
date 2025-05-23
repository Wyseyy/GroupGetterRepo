package com.example.groupgetter

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//retrofit instance for searching for tagged posts on Tumblr using a tag and api key
interface TumblrApi {
    @GET("v2/tagged")
    suspend fun searchTaggedPosts(
            @Query("tag") tag: String,
            @Query("api_key") apiKey: String
    ): Response<TumblrSearchResponse>
}