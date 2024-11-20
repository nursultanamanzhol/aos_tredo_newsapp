package kz.android.domain.usecase

import kz.android.domain.model.SavedNewsEntity
import kz.android.domain.repository.NewsRepository
import javax.inject.Inject

class SaveNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(savedNewsEntity: SavedNewsEntity) {
        repository.saveNews(savedNewsEntity)
    }
}
