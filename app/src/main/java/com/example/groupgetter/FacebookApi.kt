package com.example.groupgetter

import com.facebook.AccessToken
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FacebookApi {
    @GET("/search")
    suspend fun searchFacebookPages(
            @Query("q") query: String,
            @Query("type") type: String = "page",
            @Query("access_token") accessToken: String
    ): Response<FacebookPageSearch>
}