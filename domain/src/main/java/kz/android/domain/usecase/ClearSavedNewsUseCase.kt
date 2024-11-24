package kz.android.domain.usecase

import kz.android.domain.repository.NewsRepository
import javax.inject.Inject

class ClearSavedNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke() {
        repository.clearAllNews()
    }
}
