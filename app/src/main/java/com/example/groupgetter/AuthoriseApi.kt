package com.example.groupgetter
import retrofit2.Response
import retrofit2.http.*

interface AuthoriseApi {
    @FormUrlEncoded
    @POST("api/v1/access_token")
    suspend fun getAppOnlyAccessToken(
            @Header("Authorization") authorization: String,
            @Field("grant_type") grantType: String = "client_credentials"
    ): AuthorityResponse
}