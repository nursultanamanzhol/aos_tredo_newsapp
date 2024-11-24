package kz.android.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kz.android.ui.viewmodel.SavedNewsViewModel

@Composable
fun NewsDetailScreen(viewModel: SavedNewsViewModel = hiltViewModel()) {
    val article by viewModel.currentArticle.collectAsState()

    article?.let { nonNullArticle ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = nonNullArticle.title,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Author: ${nonNullArticle.author ?: "Unknown"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = nonNullArticle.content ?: "No content available",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } ?: run {
        Text(
            text = "No article found",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}
