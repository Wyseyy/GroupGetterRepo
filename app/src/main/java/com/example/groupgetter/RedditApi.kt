package com.example.groupgetter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RedditApi {
    @GET("subreddits/search")
    fun searchSubreddits(
            @Header("Authorization") authHeader: String,
            @Query("q") query: String,
            @Query("limit") limit: Int = 10
    ): Call<SubredditSearchResponse>
}