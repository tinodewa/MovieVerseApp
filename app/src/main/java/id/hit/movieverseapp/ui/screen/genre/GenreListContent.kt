package id.hit.movieverseapp.ui.screen.genre

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.hit.movieverseapp.common.UiState
import id.hit.movieverseapp.data.domain.model.Genre
import id.hit.movieverseapp.ui.screen.genre.component.GenreEmpty
import id.hit.movieverseapp.ui.screen.genre.component.GenreError
import id.hit.movieverseapp.ui.screen.genre.component.GenreGrid
import id.hit.movieverseapp.ui.screen.genre.component.GenreListLoading
import id.hit.movieverseapp.ui.theme.CrimsonGold
import id.hit.movieverseapp.ui.theme.ElectricCrimson
import id.hit.movieverseapp.ui.theme.ObsidianBlack
import id.hit.movieverseapp.ui.theme.OnSurfacePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreListContent(
    uiState: UiState<List<Genre>>,
    onGenreClick: (genreId: Int, genreName: String) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(ElectricCrimson, CrimsonGold)
                                    )
                                )
                                .padding(2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                                    .background(ObsidianBlack),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Movie,
                                    contentDescription = "MovieVerse Logo",
                                    tint = ElectricCrimson,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "MovieVerse",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 0.5.sp
                            ),
                            color = OnSurfacePrimary
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
            when (uiState) {
                is UiState.Loading -> {
                    GenreListLoading()
                }
                is UiState.Success -> {
                    GenreGrid(
                        genres = uiState.data,
                        onGenreClick = onGenreClick
                    )
                }
                is UiState.Empty -> {
                    GenreEmpty(onRetry = onRetry)
                }
                is UiState.Error -> {
                    GenreError(
                        message = uiState.message,
                        onRetry = onRetry
                    )
                }
            }
        }
    }
}