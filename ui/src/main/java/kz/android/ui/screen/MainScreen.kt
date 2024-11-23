package kz.android.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kz.android.ui.viewmodel.SavedNewsViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "news",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("news") {
                NewsScreen()
            }
            composable("saved") {
                SavedNewsScreen(navController)
            }
            composable("details/{articleUrl}") { backStackEntry ->
                val articleUrl = backStackEntry.arguments?.getString("articleUrl")
                val viewModel: SavedNewsViewModel = hiltViewModel()

                val article = remember(articleUrl) {
                    articleUrl?.let { viewModel.getSavedArticleByUrl(it) }
                }

                article?.let {
                    NewsDetailScreen(navController, it)
                } ?: Text("Article not found")
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = currentRoute(navController)
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "news",
            onClick = { navController.navigate("news") },
            icon = { Icon(Icons.Default.Home, contentDescription = "News") },
            label = { Text("News") }
        )
        NavigationBarItem(
            selected = currentRoute == "saved",
            onClick = { navController.navigate("saved") },
            icon = { Icon(Icons.Default.Bookmark, contentDescription = "Saved") },
            label = { Text("Saved") }
        )
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
