package kz.android.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import kz.android.ui.viewmodel.SavedNewsViewModel

@Composable
fun SavedNewsScreen(viewModel: SavedNewsViewModel = hiltViewModel()) {
    val savedNews by viewModel.savedNews.observeAsState(emptyList())
    LazyColumn {
        items(savedNews) { article ->
            Text(article.title)
            Button(onClick = { viewModel.openDetails(article) }) {
                Text("View Details")
            }
        }
    }
}
