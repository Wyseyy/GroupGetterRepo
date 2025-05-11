package com.example.groupgetter

import android.util.Log
import com.example.groupgetter.RetrofitInstance
import kotlinx.coroutines.*
import okhttp3.Credentials

//Object for fetching an app-only OAuth2 access token using Retrofit, returning it using a callback
object AuthorityRepo {
    private const val ID = "ZlbcDAFm9zETA6KWcxLAoA"
    private const val SECRET = "g-YdRKX_GYpAV5eOL0fCJ4frDhn2Vw"

    @JvmStatic
    fun getAppOnlyAccessToken(callback: (String?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val token = try {
                val basicAuthHeader = Credentials.basic(ID, SECRET)
                val response = RetrofitInstance.authoriseApi.getAppOnlyAccessToken(
                        basicAuthHeader,
                "client_credentials"
                )
                response.access_token
            } catch (e: Exception) {
                Log.e("AuthorityRepo", "Exception: ${e.message}")
                null
            }
            withContext(Dispatchers.Main) {
                callback(token)
            }
        }
    }
}
