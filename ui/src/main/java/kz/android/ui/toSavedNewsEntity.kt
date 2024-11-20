package kz.android.ui

import kz.android.domain.model.Article
import kz.android.domain.model.SavedNewsEntity

fun Article.toSavedNewsEntity(): SavedNewsEntity {
    return SavedNewsEntity(
        url = this.url,
        title = this.title,
        author = this.author,
        content = this.content,
        publishedAt = this.publishedAt
    )
}
