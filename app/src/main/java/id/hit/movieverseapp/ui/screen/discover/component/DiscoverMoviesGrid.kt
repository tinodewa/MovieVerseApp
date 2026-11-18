package id.hit.movieverseapp.ui.screen.discover.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import id.hit.movieverseapp.data.domain.model.Movie
import id.hit.movieverseapp.ui.theme.ElectricCrimson

@Composable
fun DiscoverMoviesGrid(
    movies: LazyPagingItems<Movie>,
    onMovieClick: (movieId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = movies.itemCount,
            key = { index ->
                val id = movies.peek(index)?.id
                if (id != null) "${index}_$id" else index
            },
            contentType = { "movie_item" }
        ) { index ->
            val movie = movies[index]
            if (movie != null) {
                MovieCard(
                    movie = movie,
                    onClick = { onMovieClick(movie.id) }
                )
            }
        }

        when (val appendState = movies.loadState.append) {
            is LoadState.Loading -> {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = ElectricCrimson,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }
            is LoadState.Error -> {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    FooterError(
                        message = appendState.error.localizedMessage ?: "Failed to load more movies",
                        onRetry = { movies.retry() }
                    )
                }
            }
            else -> {}
        }
    }
}
