package com.example.groupgetter

//A data class which represents the OAuth2 token structure which has been returned by the Reddit API
data class AuthorityResponse(
        val access_token: String,
        val token_type: String,
        val expires_in: Int,
        val scope: String
)
