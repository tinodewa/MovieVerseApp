package id.hit.movieverseapp.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun MovieDetailScreen(
    movieId: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(movieId) {
        viewModel.setMovieId(movieId)
    }

    val detailUiState by viewModel.detailUiState.collectAsStateWithLifecycle()
    val reviewsLazyPagingItems = viewModel.reviews.collectAsLazyPagingItems()

    MovieDetailContent(
        detailUiState = detailUiState,
        reviews = reviewsLazyPagingItems,
        onBackClick = onBackClick,
        onRetry = { viewModel.retry() },
        modifier = modifier
    )
}