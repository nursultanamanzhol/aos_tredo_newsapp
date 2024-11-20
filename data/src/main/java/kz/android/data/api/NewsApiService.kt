package kz.android.data.api

import retrofit2.http.GET
import retrofit2.http.Query

data class NewsResponse(val articles: List<ArticleDto>)

data class ArticleDto(
    val title: String,
    val author: String?,
    val content: String?,
    val url: String,
    val publishedAt: String
)

interface NewsApiService {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String
    ): NewsResponse
}
