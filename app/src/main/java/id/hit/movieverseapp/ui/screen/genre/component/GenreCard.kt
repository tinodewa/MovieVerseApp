package id.hit.movieverseapp.ui.screen.genre.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.hit.movieverseapp.data.domain.model.Genre

@Composable
fun GenreCard(
    genre: Genre,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val style = getGenreStyle(genre.name)

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(115.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 2.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = style.gradientColors,
                        start = Offset.Zero,
                        end = Offset(300f, 300f)
                    )
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(14.dp)
        ) {
            Icon(
                imageVector = style.icon,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.18f),
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.BottomEnd)
                    .scale(1.2f)
            )

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopStart),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.25f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = style.icon,
                        contentDescription = genre.name,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Text(
                    text = genre.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    ),
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}