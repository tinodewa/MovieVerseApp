package id.hit.movieverseapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import id.hit.movieverseapp.ui.screen.detail.MovieDetailScreen
import id.hit.movieverseapp.ui.screen.discover.DiscoverMoviesScreen
import id.hit.movieverseapp.ui.screen.genre.GenreListScreen

@Composable
fun MovieVerseAppNavGraph(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(Screen.GenreList)

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeLastOrNull()
            }
        },
        entryProvider = { key ->
            when (key) {
                is Screen.GenreList -> NavEntry(key) {
                    GenreListScreen(
                        onGenreClick = { genreId, genreName ->
                            backStack.add(Screen.DiscoverMovies(genreId, genreName))
                        }
                    )
                }
                is Screen.DiscoverMovies -> NavEntry(key) {
                    DiscoverMoviesScreen(
                        genreId = key.genreId,
                        genreName = key.genreName,
                        onMovieClick = { movieId ->
                            backStack.add(Screen.MovieDetail(movieId))
                        },
                        onBackClick = {
                            if (backStack.size > 1) {
                                backStack.removeLastOrNull()
                            }
                        }
                    )
                }
                is Screen.MovieDetail -> NavEntry(key) {
                    MovieDetailScreen(
                        movieId = key.movieId,
                        onBackClick = {
                            if (backStack.size > 1) {
                                backStack.removeLastOrNull()
                            }
                        }
                    )
                }
                else -> error("Unknown navigation key: $key")
            }
        }
    )
}

