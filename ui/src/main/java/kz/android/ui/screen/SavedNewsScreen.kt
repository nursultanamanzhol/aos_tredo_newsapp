package kz.android.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kz.android.domain.model.SavedNewsEntity
import kz.android.ui.viewmodel.SavedNewsViewModel

@Composable
fun SavedNewsScreen(
    navController: NavHostController,
    viewModel: SavedNewsViewModel = hiltViewModel()
) {
    val savedNews: List<SavedNewsEntity> by viewModel.savedNews.collectAsState()

    Column {
        Button(
            onClick = { viewModel.clearSavedNews() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Clear All Saved News")
        }

        if (savedNews.isEmpty()) {
            Text(
                text = "No saved news available.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        } else {
            LazyColumn {
                items(savedNews) { article ->
                    SavedNewsItem(
                        article = article,
                        onOpen = {
                            viewModel.setCurrentArticle(article)
                            navController.navigate("details")
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun SavedNewsItem(article: SavedNewsEntity, onOpen: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Author: ${article.author ?: "Unknown"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Button(onClick = onOpen) {
                Text("View Details")
            }
        }
    }
}
