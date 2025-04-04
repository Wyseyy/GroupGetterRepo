package com.example.groupgetter

import com.google.gson.annotations.SerializedName

data class AuthorityResponse(
        @SerializedName("access_token") val access_token: String,
        @SerializedName("token_type") val token_type: String,
        @SerializedName("expires_in") val expires_in: Int,
        @SerializedName("scope") val scope: String

)
