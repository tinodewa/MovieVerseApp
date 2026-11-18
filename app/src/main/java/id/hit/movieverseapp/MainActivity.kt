package id.hit.movieverseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import id.hit.movieverseapp.ui.theme.MovieVerseAppTheme
import dagger.hilt.android.AndroidEntryPoint
import id.hit.movieverseapp.ui.navigation.MovieVerseAppNavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieVerseAppTheme {
                MovieVerseAppNavGraph()
            }
        }
    }
}