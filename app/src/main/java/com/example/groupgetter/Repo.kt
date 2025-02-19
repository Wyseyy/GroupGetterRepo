package com.example.groupgetter
import android.app.DownloadManager.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repo {
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://oauth.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val redditApi = retrofit.create(RedditApi::class.java)

    fun searchSubreddits(accessToken: String, query: String, callback: (List<SubredditInformation>?) -> Unit){
        val call = redditApi.searchSubreddits("Bearer $accessToken", query)

        call.enqueue(object : retrofit2.Callback<SubredditSearchResponse> {
            override fun onResponse(call : retrofit2.Call<SubredditSearchResponse>, response: retrofit2.Response<SubredditSearchResponse>){
                if (response.isSuccessful){
                    callback(response.body()?.data?.children?.map { it.data})
                } else {
                    callback(null)
                }
            }
            override fun onFailure(call : retrofit2.Call<SubredditSearchResponse>, t: Throwable){
                callback(null)
            }
        })
    }
}