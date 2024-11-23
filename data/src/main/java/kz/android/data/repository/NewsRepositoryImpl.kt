package kz.android.data.repository

import kz.android.data.api.NewsApiService
import kz.android.data.db.NewsDao
import kz.android.data.db.SavedNews
import kz.android.domain.model.Article
import kz.android.domain.model.SavedNewsEntity
import kz.android.domain.repository.NewsRepository
import javax.inject.Inject
import javax.inject.Named

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApiService,
    private val dao: NewsDao,
    @Named("NewsApiKey") private val apiKey: String // Получаем ключ через Hilt
) : NewsRepository {

    override suspend fun fetchNews(query: String, from: String): List<Article> {
        return api.getNews(query, from, apiKey = apiKey).articles.map {
            Article(
                title = it.title,
                author = it.author,
                content = it.content,
                url = it.url,
                publishedAt = it.publishedAt
            )
        }
    }

    override suspend fun getSavedNews(): List<SavedNewsEntity> {
        return dao.getAllSavedNews().map { it.toDomain() }
    }

    override suspend fun saveNews(news: SavedNewsEntity) {
        dao.saveNews(SavedNews.fromDomain(news))
    }

}
