package com.example.groupgetter
import retrofit2.Response
import retrofit2.http.*

interface AuthoriseApi {
    @FormUrlEncoded
    @POST("api/v1/access_token")
    suspend fun getAccessToken(
            @Header("Authorise") authHeader: String,
            @Url url: String,
            @Field("grant_type") grantType: String,
            @Field("code") code: String,
            @Field("redirect_uri") redirectUri: String
    ): Response<AuthorityResponse>
}