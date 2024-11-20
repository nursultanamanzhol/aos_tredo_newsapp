package kz.android.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(news: SavedNews)

    @Query("SELECT * FROM saved_news")
    fun getAllSavedNews(): Flow<List<SavedNews>>

    @Query("SELECT * FROM saved_news WHERE url = :url")
    suspend fun getNewsByUrl(url: String): SavedNews?

}
