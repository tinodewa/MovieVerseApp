package id.hit.movieverseapp.ui.screen.genre

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun GenreListScreen(
    onGenreClick: (genreId: Int, genreName: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GenreListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GenreListContent(
        uiState = uiState,
        onGenreClick = onGenreClick,
        onRetry = { viewModel.retry() },
        modifier = modifier
    )
}