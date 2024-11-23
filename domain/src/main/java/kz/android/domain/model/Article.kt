package kz.android.domain.model

data class Article(
    val title: String,
    val author: String?,
    val content: String?,
    val url: String,
    val publishedAt: String,
)
