package kz.android.domain.usecase

import kz.android.domain.model.SavedNewsEntity
import kz.android.domain.repository.NewsRepository
import javax.inject.Inject

class GetSavedNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): List<SavedNewsEntity> {
        return repository.getSavedNews()
    }
}
