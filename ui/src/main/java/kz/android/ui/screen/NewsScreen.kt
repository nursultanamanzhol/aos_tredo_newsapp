package kz.android.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import kz.android.ui.viewmodel.NewsViewModel

@Composable
fun NewsScreen(viewModel: NewsViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val news by viewModel.news.observeAsState(emptyList())

    Column {
        SearchAndSortBar(
            onSearch = { query -> viewModel.fetchNews(query, from = "2023-10-01") },
            onSort = { viewModel.sortNewsByDate() }
        )

        LazyColumn {
            items(news) { article ->
                Text(article.title)
                Button(onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                    context.startActivity(intent)
                }) {
                    Text("Open in Browser")
                }
                Button(onClick = { viewModel.saveNews(article) }) {
                    Text("Save")
                }
            }
        }
    }
}

