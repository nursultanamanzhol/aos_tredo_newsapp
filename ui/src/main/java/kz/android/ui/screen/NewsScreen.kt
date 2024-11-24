package kz.android.ui.screen

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kz.android.ui.viewmodel.NewsViewModel
import java.util.Calendar

@Composable
fun NewsScreen(viewModel: NewsViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val news by viewModel.news.observeAsState(emptyList())
    val selectedDate = remember { mutableStateOf<String?>(null) }
    val searchQuery = remember { mutableStateOf("") }

    Column {
        SearchAndSortBar(
            onSearch = { query ->
                searchQuery.value = query
                viewModel.fetchNews(query, selectedDate.value)
            },
            onSort = { viewModel.sortNewsByDate() }
        )

        Button(onClick = {
            showDatePicker(context) { date ->
                selectedDate.value = date
                viewModel.fetchNews(searchQuery.value, date)
            }
        }) {
            Text("Select Date")
        }

        if (news.isEmpty()) {
            Text(
                text = "No news found. Try searching with different keywords.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

        } else {
            LazyColumn {
                items(news) { article ->
                    NewsItem(
                        article = article,
                        onSave = {
                            Log.d("NewsScreen", "Saving article with URL: ${article.url}")
                            viewModel.saveNews(article)
                        },
                        onOpen = {
                            val intent = Intent(context, WebViewActivity::class.java)
                            intent.putExtra("url", article.url)
                            context.startActivity(intent)
                            Log.d("NewsScreen", "Opening article with URL: ${article.url}")
                        }
                    )
                }
            }
        }
    }
}

private fun showDatePicker(context: Context, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val formattedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
            onDateSelected(formattedDate)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}
