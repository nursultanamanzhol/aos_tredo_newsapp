package kz.android.domain.repository

import kz.android.domain.model.Article
import kz.android.domain.model.SavedNewsEntity

interface NewsRepository {
    suspend fun fetchNews(query: String, from: String): List<Article>
    suspend fun saveNews(news: SavedNewsEntity)
    suspend fun getSavedNews(): List<SavedNewsEntity>
}
