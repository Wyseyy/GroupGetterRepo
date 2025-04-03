package com.example.groupgetter

import com.example.groupgetter.RetrofitInstance
import okhttp3.Credentials
import kotlinx.coroutines.*
import java.util.concurrent.CompletableFuture
import retrofit2.Retrofit
import android.util.Log
object AuthorityRepo {
    private const val ID = ""
    private const val Redirect_URI = ""
    private const val Authority_URL = ""

    suspend fun getAccessToken(): String? {
        return try {
            val authorityHeader = Credentials.basic(ID, "")
            val response = RetrofitInstance.authoriseApi.getAccessToken(
                    authorityHeader,
                    "https://www.reddit.com/api/v1/access_token",
                    "authorization_code",
                    "your_auth_code",
                    Redirect_URI
            )

            if (response.isSuccessful) {
                response.body()?.access_token
            } else {
                Log.e("AuthRepository", "Failed to get access token: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Exception: ${e.message}")
            null
        }
    }
    @JvmStatic
    fun getAccessToken(callback: (String?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val token = getAccessToken()
            withContext(Dispatchers.Main) {
                callback(token)
            }
        }
    }
}