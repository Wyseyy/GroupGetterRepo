package com.example.groupgetter

object TumblrRepo {
    private const val API_KEY = "5A4crHGM5oslQYRfg76iSIjzqg4DuVqd9bsZZh8e6EG47ysB6T "

    suspend fun searchTag(tag: String): List<TumblrPost>?{
        return try{
            val response = TumblrAuthoriseApiClient.instance.searchTaggedPosts(tag, API_KEY)
            if(response.isSuccessful){
                response.body()?.response
            } else null
        } catch (e: Exception) {
            null
        }
    }
}