package kz.android.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kz.android.domain.model.SavedNewsEntity

@Composable
fun NewsDetailScreen(navController: NavHostController, article: SavedNewsEntity) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = article.title, style = MaterialTheme.typography.headlineSmall)
        Text(text = "By: ${article.author ?: "Unknown"}", style = MaterialTheme.typography.titleSmall)
        Text(text = article.content ?: "No content available", style = MaterialTheme.typography.bodyMedium)
    }
}

