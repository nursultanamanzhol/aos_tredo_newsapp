package kz.android.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kz.android.ui.viewmodel.SavedNewsViewModel

@Composable
fun SavedNewsScreen(
    navController: NavController,
    viewModel: SavedNewsViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.fetchSavedNews()
    }

    val savedNews by viewModel.savedNews.observeAsState(emptyList())
    LazyColumn {
        items(savedNews) { article ->
            Text(article.title)
            Button(onClick = {
                navController.navigate("details/${article.url}")
            }) {
                Text("View Details")
            }
        }
    }
}
