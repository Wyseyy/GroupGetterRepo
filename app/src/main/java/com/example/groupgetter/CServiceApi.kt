package com.example.groupgetter

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class CServiceRequest(
        val name: String,
        val email: String,
        val message: String
)

interface CServiceApi {
    @POST("/support")
    fun sendMessage(@Body request: CServiceRequest): Call<Void>
}