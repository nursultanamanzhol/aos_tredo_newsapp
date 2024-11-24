package kz.android.domain.usecase

import android.util.Log
import kz.android.domain.model.SavedNewsEntity
import kz.android.domain.repository.NewsRepository
import javax.inject.Inject

class SaveNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(news: SavedNewsEntity) {
        Log.d("SaveNewsUseCase", "Saving news entity: $news")
        repository.saveNews(news)
    }

}
