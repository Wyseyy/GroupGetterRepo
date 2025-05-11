package com.example.groupgetter

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

//Data class representing the structure of a support request sent by a user
data class CServiceRequest(
        val name: String,
        val email: String,
        val message: String
)

//Retrofit instance for sending the message to the support email
interface CServiceApi {
    @POST("/support")
    fun sendMessage(@Body request: CServiceRequest): Call<Void>
}