package kz.android.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController

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
            composable("news") { NewsScreen() }
            composable("saved") { SavedNewsScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = navController.currentDestination?.route
    BottomNavigation {
        BottomNavigationItem(
            selected = currentRoute == "news",
            onClick = { navController.navigate("news") },
            icon = { Icon(Icons.Default.Home, contentDescription = "News") },
            label = { Text("News") }
        )
        BottomNavigationItem(
            selected = currentRoute == "saved",
            onClick = { navController.navigate("saved") },
            icon = { Icon(Icons.Default.Bookmark, contentDescription = "Saved") },
            label = { Text("Saved") }
        )
    }
}
