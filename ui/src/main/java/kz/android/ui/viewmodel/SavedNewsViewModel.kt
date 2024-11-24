package kz.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kz.android.domain.model.SavedNewsEntity
import kz.android.domain.repository.NewsRepository
import kz.android.domain.usecase.ClearSavedNewsUseCase
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val clearSavedNewsUseCase: ClearSavedNewsUseCase
) : ViewModel() {

    // Используем MutableStateFlow вместо LiveData
    private val _currentArticle = MutableStateFlow<SavedNewsEntity?>(null)
    val currentArticle: StateFlow<SavedNewsEntity?> = _currentArticle.asStateFlow()

    // Подгружаем сохраненные новости из репозитория
    val savedNews: StateFlow<List<SavedNewsEntity>> = repository.getAllSavedNews()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Устанавливаем текущую статью
    fun setCurrentArticle(article: SavedNewsEntity) {
        _currentArticle.value = article
    }

    // Обновляем новость
    fun updateNews(news: SavedNewsEntity) {
        viewModelScope.launch {
            repository.updateNews(news)
        }
    }

    // Очищаем все сохраненные новости
    fun clearSavedNews() {
        viewModelScope.launch {
            clearSavedNewsUseCase()
        }
    }
}
