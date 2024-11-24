package kz.android.data.db

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(news: SavedNews)

    @Query("SELECT * FROM saved_news")
    suspend fun getAllSavedNews(): List<SavedNews> {
        val allNews = getAllSavedNewsInternal()
        Log.d("NewsDao", "Fetched all saved news: $allNews")
        return allNews
    }

    @Query("SELECT * FROM saved_news")
    suspend fun getAllSavedNewsInternal(): List<SavedNews>

    @Query("SELECT * FROM saved_news WHERE url = :url")
    suspend fun getNewsByUrl(url: String): SavedNews? {
        Log.d("NewsDao", "Querying database for URL: $url")
        val result = getNewsByUrlInternal(url)
        Log.d("NewsDao", "Result for URL: $result")
        return result
    }

    @Query("SELECT * FROM saved_news WHERE url = :url")
    suspend fun getNewsByUrlInternal(url: String): SavedNews?

    @Query("DELETE FROM saved_news")
    suspend fun clearSavedNews()
}
