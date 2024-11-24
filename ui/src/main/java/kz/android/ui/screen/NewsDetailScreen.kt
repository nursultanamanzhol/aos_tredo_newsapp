package kz.android.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kz.android.ui.viewmodel.SavedNewsViewModel

@Composable
fun NewsDetailScreen(viewModel: SavedNewsViewModel = hiltViewModel()) {
    val article by viewModel.currentArticle.observeAsState()
    Log.d("NewsDetailScreen", "Current article: $article")
    if (article == null) {
        Text(
            text = "No article found",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    } else {
        article?.let {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "By: ${it.author ?: "Unknown"}",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = it.content ?: "No content available",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

