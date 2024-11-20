package kz.android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kz.android.domain.model.SavedNewsEntity
import kz.android.domain.usecase.GetSavedNewsUseCase
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val getSavedNewsUseCase: GetSavedNewsUseCase
) : ViewModel() {

    private val _savedNews = MutableLiveData<List<SavedNewsEntity>>()
    val savedNews: LiveData<List<SavedNewsEntity>> get() = _savedNews

    fun fetchSavedNews() {
        viewModelScope.launch {
            _savedNews.value = getSavedNewsUseCase.invoke()
        }
    }


    fun openDetails(article: SavedNewsEntity) {
        // Логика открытия деталей статьи
    }
}
