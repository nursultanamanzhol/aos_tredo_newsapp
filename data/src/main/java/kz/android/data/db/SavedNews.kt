package kz.android.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import kz.android.domain.model.SavedNewsEntity

@Entity(tableName = "saved_news")
data class SavedNews(
    @PrimaryKey val url: String,
    val title: String,
    val author: String?,
    val content: String?,
    val publishedAt: String
) {
    fun toDomain(): SavedNewsEntity = SavedNewsEntity(
        url = url,
        title = title,
        author = author,
        content = content,
        publishedAt = publishedAt
    )

    companion object {
        fun fromDomain(savedNewsEntity: SavedNewsEntity): SavedNews {
            return SavedNews(
                url = savedNewsEntity.url,
                title = savedNewsEntity.title,
                author = savedNewsEntity.author,
                content = savedNewsEntity.content,
                publishedAt = savedNewsEntity.publishedAt
            )
        }
    }
}
