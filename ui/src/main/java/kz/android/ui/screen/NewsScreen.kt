package kz.android.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import kz.android.ui.viewmodel.NewsViewModel

@Composable
fun NewsScreen(viewModel: NewsViewModel = hiltViewModel()) {
    val news by viewModel.news.observeAsState(emptyList())
    LazyColumn {
        items(news) { article ->
            Text(article.title)
            Button(onClick = { viewModel.saveNews(article) }) {
                Text("Save")
            }
        }
    }
}
