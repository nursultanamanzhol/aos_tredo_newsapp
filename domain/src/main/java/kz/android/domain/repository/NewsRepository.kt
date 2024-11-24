package kz.android.domain.repository

import kotlinx.coroutines.flow.Flow
import kz.android.domain.model.Article
import kz.android.domain.model.SavedNewsEntity

interface NewsRepository {
    fun getAllSavedNews(): Flow<List<SavedNewsEntity>>
    fun getNewsByUrl(url: String): Flow<SavedNewsEntity?>
    suspend fun saveNews(news: SavedNewsEntity)
    suspend fun updateNews(news: SavedNewsEntity)
    suspend fun clearAllNews()
    suspend fun fetchNews(query: String, from: String?): List<Article>
    suspend fun getSavedNews(): List<SavedNewsEntity>
}
