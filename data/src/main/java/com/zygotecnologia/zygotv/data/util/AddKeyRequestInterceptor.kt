package com.zygotecnologia.zygotv.data.util

import android.content.Context
import com.zygotecnologia.zygotv.data.R
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AddKeyRequestInterceptor(private val context: Context): Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl: HttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", context.getString(R.string.TMDB_API_KEY))
            .addQueryParameter("region", context.getString(R.string.TMDB_REGION))
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}