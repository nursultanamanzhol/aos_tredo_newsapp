package kz.android.data.repository

import kz.android.data.api.NewsApiService
import kz.android.domain.model.Article
import kz.android.data.db.NewsDao
import kz.android.data.db.SavedNews
import kz.android.domain.model.SavedNewsEntity
import kz.android.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kz.android.data.api.NewsApiHelper
import kz.android.data.api.models.toDomain
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val newsApiHelper: NewsApiHelper // Добавлено
) : NewsRepository {

    override fun getAllSavedNews(): Flow<List<SavedNewsEntity>> {
        return newsDao.getAllSavedNews().map { newsList ->
            newsList.map { it.toDomain() }
        }
    }

    override fun getNewsByUrl(url: String): Flow<SavedNewsEntity?> {
        return newsDao.getNewsByUrl(url).map { it?.toDomain() }
    }

    override suspend fun saveNews(news: SavedNewsEntity) {
        newsDao.saveNews(SavedNews.fromDomain(news))
    }

    override suspend fun updateNews(news: SavedNewsEntity) {
        newsDao.updateNews(SavedNews.fromDomain(news))
    }

    override suspend fun clearAllNews() {
        newsDao.clearSavedNews()
    }

    // Новый метод
    override suspend fun fetchNews(query: String, from: String?): List<Article> {
        return newsApiHelper.fetchNews(query, from).articles.map { it.toDomain() }
    }
    override suspend fun getSavedNews(): List<SavedNewsEntity> {
        return newsDao.getAllSavedNewsInternal().map { it.toDomain() }
    }

}
