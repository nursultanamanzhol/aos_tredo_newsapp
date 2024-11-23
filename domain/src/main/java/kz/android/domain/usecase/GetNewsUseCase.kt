package kz.android.domain.usecase

import kz.android.domain.model.Article
import kz.android.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
) {
    suspend operator fun invoke(query: String, from: String): List<Article> {
        return repository.fetchNews(query, from)
    }
}
