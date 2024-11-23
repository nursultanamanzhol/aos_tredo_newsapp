package kz.android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kz.android.domain.model.Article
import kz.android.domain.usecase.GetNewsUseCase
import kz.android.domain.usecase.SaveNewsUseCase
import kz.android.ui.toSavedNewsEntity
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase
) : ViewModel() {

    private val _news = MutableLiveData<List<Article>>()
    val news: LiveData<List<Article>> get() = _news

    fun fetchNews(query: String, from: String) {
        if (query.isBlank()) {
            _news.value = emptyList()
            return
        }
        viewModelScope.launch {
            _news.value = getNewsUseCase(query, from)
        }
    }

    fun sortNewsByDate() {
        _news.value = _news.value?.sortedByDescending { it.publishedAt }
    }

    fun saveNews(article: Article) {
        viewModelScope.launch {
            saveNewsUseCase.invoke(article.toSavedNewsEntity())
        }
    }
}
