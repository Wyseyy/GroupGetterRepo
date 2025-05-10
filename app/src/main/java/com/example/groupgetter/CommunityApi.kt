package com.example.groupgetter

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CommunityApi {
    @GET("community/messages")
    fun getMessages(): Call<List<Message>>

    @POST("community/messages")
    fun postMessages(@Body message: Message): Call<Void>
}