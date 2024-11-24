package kz.android.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kz.android.domain.model.SavedNewsEntity
import kz.android.domain.usecase.ClearSavedNewsUseCase
import kz.android.domain.usecase.GetSavedNewsUseCase
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val clearSavedNewsUseCase: ClearSavedNewsUseCase
) : ViewModel() {

    private val _savedNews = MutableLiveData<List<SavedNewsEntity>>()
    val savedNews: LiveData<List<SavedNewsEntity>> get() = _savedNews

    private val _currentArticle = MutableLiveData<SavedNewsEntity?>()
    val currentArticle: LiveData<SavedNewsEntity?> get() = _currentArticle

    init {
        fetchSavedNews()
    }

    fun selectArticle(article: SavedNewsEntity) {
        Log.d("SavedNewsViewModel", "Selected article: $article")
        _currentArticle.value = article
    }


    fun fetchSavedNews() {
        viewModelScope.launch {
            try {
                val data = getSavedNewsUseCase.invoke()
                _savedNews.postValue(data)
            } catch (e: Exception) {
                Log.e("SavedNewsViewModel", "Error fetching saved news: ${e.message}")
            }
        }
    }

    fun clearSavedNews() {
        viewModelScope.launch {
            try {
                clearSavedNewsUseCase.invoke()
                fetchSavedNews()
            } catch (e: Exception) {
                Log.e("SavedNewsViewModel", "Error clearing saved news: ${e.message}")
            }
        }
    }
}
