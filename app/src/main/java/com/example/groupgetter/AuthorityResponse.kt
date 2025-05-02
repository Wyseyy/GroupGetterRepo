package com.example.groupgetter

data class AuthorityResponse(
        val access_token: String,
        val token_type: String,
        val expires_in: Int,
        val scope: String
)
