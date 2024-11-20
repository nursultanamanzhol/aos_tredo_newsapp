package kz.android.aos_tredo_newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import kz.android.aos_tredo_newsapp.ui.theme.Aos_tredo_newsappTheme
import kz.android.ui.screen.MainScreen

@AndroidEntryPoint // Добавляем Hilt для внедрения зависимостей
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aos_tredo_newsappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MainScreen() // Здесь вызывается главный экран с навигацией
                }
            }
        }
    }
}
