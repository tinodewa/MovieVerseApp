package id.hit.movieverseapp.ui.screen.detail

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
import androidx.paging.compose.LazyPagingItems
import id.hit.movieverseapp.data.domain.model.Review
import id.hit.movieverseapp.ui.screen.detail.component.MovieDetailError
import id.hit.movieverseapp.ui.screen.detail.component.MovieDetailShimmer
import id.hit.movieverseapp.ui.screen.detail.component.MovieDetailSuccess
import id.hit.movieverseapp.ui.theme.OnSurfacePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailContent(
    detailUiState: DetailUiState,
    reviews: LazyPagingItems<Review>,
    onBackClick: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    val title = (detailUiState as? DetailUiState.Success)?.movieDetail?.title ?: "Movie Details"
                    Text(
                        text = title,
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
                    containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.95f),
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
            when (detailUiState) {
                is DetailUiState.Loading -> {
                    MovieDetailShimmer()
                }
                is DetailUiState.Error -> {
                    MovieDetailError(
                        message = detailUiState.message,
                        onRetry = onRetry
                    )
                }
                is DetailUiState.Success -> {
                    MovieDetailSuccess(
                        detail = detailUiState.movieDetail,
                        trailers = detailUiState.trailers,
                        reviews = reviews
                    )
                }
            }
        }
    }
}