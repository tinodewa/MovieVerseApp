package id.hit.movieverseapp.ui.screen.genre.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.hit.movieverseapp.data.domain.model.Genre

@Composable
fun GenreGrid(
    genres: List<Genre>,
    onGenreClick: (genreId: Int, genreName: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(genres, key = { it.id }) { genre ->
            GenreCard(
                genre = genre,
                onClick = { onGenreClick(genre.id, genre.name) }
            )
        }
    }
}