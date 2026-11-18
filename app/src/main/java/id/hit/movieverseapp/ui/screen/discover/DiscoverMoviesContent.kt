package id.hit.movieverseapp.ui.screen.discover

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import id.hit.movieverseapp.data.domain.model.Movie
import id.hit.movieverseapp.ui.screen.discover.component.DiscoverMoviesEmpty
import id.hit.movieverseapp.ui.screen.discover.component.DiscoverMoviesError
import id.hit.movieverseapp.ui.screen.discover.component.DiscoverMoviesGrid
import id.hit.movieverseapp.ui.screen.discover.component.DiscoverMoviesShimmerGrid
import id.hit.movieverseapp.ui.theme.OnSurfacePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverMoviesContent(
    genreName: String,
    movies: LazyPagingItems<Movie>,
    onMovieClick: (movieId: Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (genreName.isNotEmpty())
                            "$genreName Movies"
                        else
                            "Discover Movies",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = OnSurfacePrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back",
                            tint = OnSurfacePrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = OnSurfacePrimary
                ),
                modifier = Modifier.shadow(4.dp)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val refreshState = movies.loadState.refresh

            when {
                refreshState is LoadState.Loading -> {
                    DiscoverMoviesShimmerGrid()
                }
                refreshState is LoadState.Error -> {
                    val errorMessage = refreshState.error.localizedMessage
                        ?: "An error occurred while loading movies."
                    DiscoverMoviesError(
                        message = errorMessage,
                        onRetry = { movies.retry() }
                    )
                }
                refreshState is LoadState.NotLoading && movies.itemCount == 0 -> {
                    DiscoverMoviesEmpty(
                        genreName = genreName,
                        onRetry = { movies.retry() }
                    )
                }
                else -> {
                    DiscoverMoviesGrid(
                        movies = movies,
                        onMovieClick = onMovieClick
                    )
                }
            }
        }
    }
}