package kz.android.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchAndSortBar(onSearch: (String) -> Unit, onSort: () -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                if (it.length >= 3) { // Начинаем поиск только после 3 символов
                    onSearch(it)
                }
            },
            label = { Text("Search News") },
            modifier = Modifier.weight(1f)
        )
        Button(onClick = onSort, modifier = Modifier.padding(start = 8.dp)) {
            Text("Sort by Date")
        }
    }
}
