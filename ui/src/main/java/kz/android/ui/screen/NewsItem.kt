package kz.android.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.android.domain.model.Article

@Composable
fun NewsItem(
    article: Article,
    onSave: () -> Unit,
    onOpen: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = article.title.takeIf { it.isNotEmpty() } ?: "Untitled",
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 2
            )
            Text(
                text = "Published: ${article.publishedAt.takeIf { it.isNotEmpty() } ?: "Unknown"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = onOpen) {
                    Text("Open")
                }
                Button(onClick = onSave) {
                    Text("Save")
                }
            }
        }
    }
}
