package id.hit.movieverseapp.ui.screen.genre.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MovieFilter
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.hit.movieverseapp.ui.theme.CrimsonGold
import id.hit.movieverseapp.ui.theme.ElectricCrimson
import id.hit.movieverseapp.ui.theme.OnSurfacePrimary

@Composable
fun GenreEmpty(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Rounded.MovieFilter,
            contentDescription = "Empty Genres",
            tint = CrimsonGold,
            modifier = Modifier.size(72.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No Movie Genres Found",
            style = MaterialTheme.typography.headlineMedium,
            color = OnSurfacePrimary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "We couldn't find any movie genres right now.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedButton(
            onClick = onRetry,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = CrimsonGold
            ),
            border = BorderStroke(
                width = 1.dp,
                brush = Brush.horizontalGradient(listOf(CrimsonGold, ElectricCrimson))
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Refresh,
                contentDescription = "Refresh",
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Refresh")
        }
    }
}