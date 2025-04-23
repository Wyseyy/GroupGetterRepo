package com.example.groupgetter

import com.example.groupgetter.RetrofitInstance
import okhttp3.Credentials
import kotlinx.coroutines.*
import java.util.concurrent.CompletableFuture
import retrofit2.Retrofit
import android.util.Log
object AuthorityRepo {
    private const val ID = "ZlbcDAFm9zETA6KWcxLAoA"
    private const val SECRET = "g-YdRKX_GYpAV5eOL0fCJ4frDhn2Vw"
    private const val Redirect_URI = "http://groupgetter://redditredirect"
    @JvmStatic
    fun getAccessToken(authCode: String, callback: (String?) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val token = try {
                val header = Credentials.basic(ID, SECRET)
                val response = RetrofitInstance.authoriseApi.getAccessToken(
                        header,
                        "https://www.reddit.com/api/v1/access_token",
                    "authorization_code",
                        authCode,
                        Redirect_URI
                )
                response.body()?.access_token
            } catch (e: Exception) {
                Log.e("AuthRepository", "Exception: ${e.message}")
                null
            }
            withContext(Dispatchers.Main){
                callback(token)
            }
        }
    }
}