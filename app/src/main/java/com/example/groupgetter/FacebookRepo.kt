package com.example.groupgetter

import android.app.DownloadManager.Query
import android.util.Log
import com.facebook.AccessToken

object FacebookRepo {
    suspend fun searchForPages(query: String, accessToken: String): List<FacebookPages>? {
        return try{
            val response = FacebookRetrofit.api.searchFacebookPages(query, accessToken = accessToken)
            if (response.isSuccessful){
                response.body()?.data
            } else {
                Log.e("FacebookRepo", "Error: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            Log.e("FacebookRepo", "Exception: ${e.message}")
            null
        }
    }
}