package kz.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SavedNews::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}