package kz.android.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kz.android.ui.navigation.BottomNavigationBar
import kz.android.ui.navigation.Routes
import kz.android.ui.viewmodel.SavedNewsViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val savedNewsViewModel: SavedNewsViewModel = hiltViewModel() // Создание ViewModel здесь

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.NEWS,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.NEWS) {
                NewsScreen()
            }
            composable(Routes.SAVED) {
                SavedNewsScreen(navController, savedNewsViewModel) // Передача ViewModel
            }
            composable("details") {
                NewsDetailScreen(savedNewsViewModel) // Передача той же ViewModel
            }
        }
    }
}


@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
