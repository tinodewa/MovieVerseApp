package id.hit.movieverseapp.ui.screen.discover

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun DiscoverMoviesScreen(
    genreId: Int,
    genreName: String,
    onMovieClick: (movieId: Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DiscoverMoviesViewModel = hiltViewModel()
) {
    LaunchedEffect(genreId, genreName) {
        viewModel.setGenre(genreId, genreName)
    }

    val moviesLazyPagingItems = viewModel.movies.collectAsLazyPagingItems()
    val currentGenreName by viewModel.genreName.collectAsStateWithLifecycle()

    DiscoverMoviesContent(
        genreName = currentGenreName.ifEmpty { genreName },
        movies = moviesLazyPagingItems,
        onMovieClick = onMovieClick,
        onBackClick = onBackClick,
        modifier = modifier
    )
}






