package com.example.groupgetter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.Query
import kotlinx.coroutines.*
import java.util.concurrent.CompletableFuture
object Repo {
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://oauth.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val redditApi = retrofit.create(RedditApi::class.java)
    private val scope = CoroutineScope(Dispatchers.IO)
    @JvmStatic
    fun searchSubredditLaunch(accessToken: String, query: String): CompletableFuture<List<SubredditInformation>?> {
        val launch = CompletableFuture<List<SubredditInformation>?>()
        scope.launch {
            try {
                val result = SubredditSearch(accessToken, query)
                launch.complete(result)
            } catch (e: Exception) {
                launch.completeExceptionally(e)
            }
        }
        return launch
    }

    suspend fun SubredditSearch(accessToken: String, query: String): List<SubredditInformation>? {
        return try {
            val reply = redditApi.searchSubreddits("Bearer $accessToken", query)
            if (reply.isSuccessful) {
                reply.body()?.data?.children?.map { it.data } ?: emptyList()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}