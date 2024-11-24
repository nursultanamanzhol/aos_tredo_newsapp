package kz.android.data.api

import kz.android.data.api.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Named

interface NewsApiService {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("from") from: String? = null,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}

class NewsApiHelper @Inject constructor(
    private val newsApiService: NewsApiService,
    @Named("NewsApiKey") private val apiKey: String
) {
    suspend fun fetchNews(query: String, from: String?): NewsResponse {
        return newsApiService.getNews(query, from, apiKey)
    }
}
