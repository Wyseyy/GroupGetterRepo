package com.example.groupgetter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RedditApi {
    @Headers(
            "User-Agent: GroupGetterApp/0.1 by YourRedditUsername"
    )
    @GET("subreddits/search")
    suspend fun searchSubreddits(
            @Header("Authorization") authHeader: String,
            @Query("q") query: String,
            @Query("limit") limit: Int = 10
    ): retrofit2.Response<SubredditSearchResponse>
}