package kz.android.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kz.android.ui.screen.currentRoute

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = currentRoute(navController)
    NavigationBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        NavigationBarItem(
            selected = currentRoute == "news",
            onClick = { navController.navigate("news") },
            icon = { Icon(Icons.Default.Home, contentDescription = "News") },
            label = { Text("News", color = MaterialTheme.colorScheme.onPrimaryContainer) }
        )
        NavigationBarItem(
            selected = currentRoute == "saved",
            onClick = { navController.navigate("saved") },
            icon = { Icon(Icons.Default.Bookmark, contentDescription = "Saved") },
            label = { Text("Saved", color = MaterialTheme.colorScheme.onPrimaryContainer) }
        )
    }
}