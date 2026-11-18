package id.hit.movieverseapp.data.sources.remote

import id.hit.movieverseapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(
    private val apiKey: String = BuildConfig.TMDB_API_KEY
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = if (apiKey.startsWith("eyJ")) {
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $apiKey")
                .build()
        } else {
            val originalUrl = originalRequest.url
            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()
            originalRequest.newBuilder()
                .url(newUrl)
                .build()
        }

        return chain.proceed(newRequest)
    }
}