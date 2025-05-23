package com.example.groupgetter

// Object providing a function to search for tagged posts on Tumblr
// using the provided API key and handling the response.
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