package kz.android.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(news: SavedNews)

    @Query("SELECT * FROM saved_news")
    fun getAllSavedNews(): Flow<List<SavedNews>>

    @Query("SELECT * FROM saved_news WHERE url = :url")
    fun getNewsByUrl(url: String): Flow<SavedNews?>

    @Query("DELETE FROM saved_news")
    suspend fun clearSavedNews()

    @Update
    suspend fun updateNews(news: SavedNews)

    @Query("SELECT * FROM saved_news")
    suspend fun getAllSavedNewsInternal(): List<SavedNews>
}
