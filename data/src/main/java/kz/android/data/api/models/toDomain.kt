package kz.android.data.api.models

import kz.android.domain.model.Article

fun ArticleDto.toDomain(): Article {
    return Article(
        title = title,
        author = author,
        content = content,
        url = url,
        publishedAt = publishedAt
    )
}
