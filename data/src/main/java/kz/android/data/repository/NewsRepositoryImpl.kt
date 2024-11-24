package kz.android.data.repository

import android.util.Log
import kz.android.data.api.NewsApiService
import kz.android.data.db.NewsDao
import kz.android.data.db.SavedNews
import kz.android.domain.model.Article
import kz.android.domain.model.SavedNewsEntity
import kz.android.domain.repository.NewsRepository
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Named

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApiService,
    private val dao: NewsDao,
    @Named("NewsApiKey") private val apiKey: String // Получаем ключ через Hilt
) : NewsRepository {

    override suspend fun fetchNews(query: String, from: String?): List<Article> {
        return try {
            val response = if (from.isNullOrEmpty()) {
                api.getNews(query = query, apiKey = apiKey)
            } else {
                api.getNews(query = query, from = from, apiKey = apiKey)
            }
            if (response.status == "ok") {
                response.articles.map {
                    Article(
                        title = it.title,
                        author = it.author,
                        content = it.content,
                        url = it.url,
                        publishedAt = it.publishedAt
                    )
                }
            } else {
                Log.e("NewsRepository", "Error fetching news: ${response.status}")
                emptyList()
            }
        } catch (e: HttpException) {
            Log.e("NewsRepository", "HTTP error: ${e.code()}")
            emptyList()
        } catch (e: Exception) {
            Log.e("NewsRepository", "Unexpected error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getSavedNews(): List<SavedNewsEntity> {
        return dao.getAllSavedNews().map { it.toDomain() }
    }

    override suspend fun saveNews(news: SavedNewsEntity) {
        Log.d("NewsRepository", "Saving news to database: $news")
        dao.saveNews(SavedNews.fromDomain(news))
    }

    override suspend fun getSavedNewsByUrl(url: String): SavedNewsEntity? {
        return dao.getNewsByUrl(url)?.toDomain()
    }

    override suspend fun clearSavedNews() {
        dao.clearSavedNews() // Реализуем метод очистки через DAO
    }
}
