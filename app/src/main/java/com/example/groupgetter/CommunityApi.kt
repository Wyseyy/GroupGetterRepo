package com.example.groupgetter

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//Retrofit instance for both retrieving and posting messages in the community page via API endpoints
interface CommunityApi {
    @GET("community/messages")
    fun getMessages(): Call<List<Message>>

    @POST("community/messages")
    fun postMessages(@Body message: Message): Call<Void>
}