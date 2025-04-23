package com.example.groupgetter

data class FacebookPageSearch(
        val data: List<FacebookPages>
)

data class FacebookPages(
        val id: String,
        val name: String,
        val category: String
)
