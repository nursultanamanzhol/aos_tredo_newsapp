package kz.android.ui.viewmodel

import android.util.Log
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
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase
) : ViewModel() {

    private val _news = MutableLiveData<List<Article>>()
    val news: LiveData<List<Article>> get() = _news

    fun fetchNews(query: String, from: String? = null) {
        if (query.isBlank()) {
            _news.value = emptyList()
            return
        }
        viewModelScope.launch {
            try {
                val news = if (from.isNullOrEmpty()) {
                    getNewsUseCase(query, null)
                } else {
                    getNewsUseCase(query, from)
                }
                _news.value = news
            } catch (e: HttpException) {
                val code = e.response()?.code()
                Log.e("NewsViewModel", "HTTP ${code ?: "Unknown"}: ${e.message}")
            } catch (e: Exception) {
                Log.e("NewsViewModel", "Error fetching news: ${e.message}")
            }
        }
    }

    fun sortNewsByDate() {
        _news.value = _news.value?.sortedByDescending { it.publishedAt }
    }

    fun saveNews(article: Article) {
        val trimmedUrl = article.url.trim() // Убираем лишние пробелы из URL
        Log.d("NewsViewModel", "Saving article with URL: $trimmedUrl")
        viewModelScope.launch {
            saveNewsUseCase.invoke(article.toSavedNewsEntity().copy(url = trimmedUrl))
        }
    }
}
