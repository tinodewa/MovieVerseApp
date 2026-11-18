package id.hit.movieverseapp.ui.screen.detail.component

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import id.hit.movieverseapp.data.domain.model.MovieDetail
import id.hit.movieverseapp.data.domain.model.Review
import id.hit.movieverseapp.data.domain.model.Video
import id.hit.movieverseapp.ui.theme.ElectricCrimson
import id.hit.movieverseapp.ui.theme.OnSurfacePrimary
import androidx.core.net.toUri
import id.hit.movieverseapp.ui.screen.detail.component.EmptyTrailerCard

@Composable
fun MovieDetailSuccess(
    detail: MovieDetail,
    trailers: List<Video>,
    reviews: LazyPagingItems<Review>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val youtubeTrailer = trailers.firstOrNull {
        it.site.equals("YouTube", ignoreCase = true) &&
                it.type.equals("Trailer", ignoreCase = true)
    } ?: trailers.firstOrNull { it.site.equals("YouTube", ignoreCase = true) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // Backdrop & Poster Header Section
        item {
            MovieHeaderSection(detail = detail)
        }

        // Synopsis Section
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Synopsis",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = OnSurfacePrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = detail.overview.ifBlank { "No synopsis available." },
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 22.sp
                )
            }
        }

        // Trailer Section
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Trailer",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = OnSurfacePrimary
                )
                Spacer(modifier = Modifier.height(8.dp))

                if (youtubeTrailer != null) {
                    var isPlayingTrailer by rememberSaveable (youtubeTrailer.key) { mutableStateOf(false) }

                    val openInYoutubeApp = {
                        try {
                            val appIntent = Intent(
                                Intent.ACTION_VIEW,
                                "vnd.youtube:${youtubeTrailer.key}".toUri()
                            )
                            context.startActivity(appIntent)
                        } catch (_: ActivityNotFoundException) {
                            try {
                                val webIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    youtubeTrailer.youtubeUrl.toUri()
                                )
                                context.startActivity(webIntent)
                            } catch (_: Exception) {}
                        }
                    }

                    if (isPlayingTrailer) {
                        MovieTrailerSection(
                            youtubeVideoKey = youtubeTrailer.key,
                            onOpenExternalClick = openInYoutubeApp,
                            onCloseClick = { isPlayingTrailer = false }
                        )
                    } else {
                        TrailerCard(
                            trailer = youtubeTrailer,
                            onPlayClick = {
                                isPlayingTrailer = true
                            }
                        )
                    }
                } else {
                    EmptyTrailerCard()
                }
            }
        }

        // Reviews Section Title
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
            ) {
                Text(
                    text = "User Reviews",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = OnSurfacePrimary
                )
            }
        }

        // Review Items from PagingData
        items(
            count = reviews.itemCount,
            key = { index -> reviews[index]?.id ?: index }
        ) { index ->
            val review = reviews[index]
            if (review != null) {
                ReviewCard(
                    review = review,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }
        }

        // Review Loading / Empty / Append Error states
        val refreshState = reviews.loadState.refresh
        val appendState = reviews.loadState.append

        if (refreshState is LoadState.Loading && reviews.itemCount == 0) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = ElectricCrimson)
                }
            }
        } else if (refreshState is LoadState.NotLoading && reviews.itemCount == 0) {
            item {
                EmptyReviewCard(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }

        when (appendState) {
            is LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = ElectricCrimson,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
            is LoadState.Error -> {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.errorContainer)
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = appendState.error.localizedMessage ?: "Failed to load more reviews",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = { reviews.retry() },
                            colors = ButtonDefaults.buttonColors(containerColor = ElectricCrimson)
                        ) {
                            Text(text = "Retry", style = MaterialTheme.typography.labelMedium)
                        }
                    }
                }
            }
            else -> {}
        }
    }
}