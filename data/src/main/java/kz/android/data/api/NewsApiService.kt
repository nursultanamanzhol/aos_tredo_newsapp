package kz.android.data.api

import kz.android.data.api.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("from") from: String? = null, // nullable
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String,
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String
    ): NewsResponse
}
