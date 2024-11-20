package kz.android.domain.model

data class SavedNewsEntity(
    val url: String,
    val title: String,
    val author: String?,
    val content: String?,
    val publishedAt: String
)
