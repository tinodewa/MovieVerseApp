package id.hit.movieverseapp.ui.screen.genre.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.AutoStories
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.FamilyRestroom
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.MilitaryTech
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.Nightlight
import androidx.compose.material.icons.rounded.Psychology
import androidx.compose.material.icons.rounded.RocketLaunch
import androidx.compose.material.icons.rounded.SentimentVerySatisfied
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.TheaterComedy
import androidx.compose.material.icons.rounded.Tv
import androidx.compose.material.icons.rounded.Videocam
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class GenreStyle(
    val gradientColors: List<Color>,
    val icon: ImageVector
)

fun getGenreStyle(genreName: String): GenreStyle {
    return when (genreName.lowercase()) {
        "action" -> GenreStyle(
            gradientColors = listOf(Color(0xFFB71C1C), Color(0xFFE50914)),
            icon = Icons.Rounded.LocalFireDepartment
        )
        "adventure" -> GenreStyle(
            gradientColors = listOf(Color(0xFFE65100), Color(0xFFFF8F00)),
            icon = Icons.Rounded.Explore
        )
        "animation" -> GenreStyle(
            gradientColors = listOf(Color(0xFF4A148C), Color(0xFF8E24AA)),
            icon = Icons.Rounded.AutoAwesome
        )
        "comedy" -> GenreStyle(
            gradientColors = listOf(Color(0xFFF57F17), Color(0xFFFFB300)),
            icon = Icons.Rounded.SentimentVerySatisfied
        )
        "crime" -> GenreStyle(
            gradientColors = listOf(Color(0xFF263238), Color(0xFF37474F)),
            icon = Icons.Rounded.Shield
        )
        "documentary" -> GenreStyle(
            gradientColors = listOf(Color(0xFF006064), Color(0xFF00838F)),
            icon = Icons.Rounded.Videocam
        )
        "drama" -> GenreStyle(
            gradientColors = listOf(Color(0xFF311B92), Color(0xFF5E35B1)),
            icon = Icons.Rounded.TheaterComedy
        )
        "family" -> GenreStyle(
            gradientColors = listOf(Color(0xFF1B5E20), Color(0xFF388E3C)),
            icon = Icons.Rounded.FamilyRestroom
        )
        "fantasy" -> GenreStyle(
            gradientColors = listOf(Color(0xFF880E4F), Color(0xFFC2185B)),
            icon = Icons.Rounded.AutoAwesome
        )
        "history" -> GenreStyle(
            gradientColors = listOf(Color(0xFF3E2723), Color(0xFF5D4037)),
            icon = Icons.Rounded.AutoStories
        )
        "horror" -> GenreStyle(
            gradientColors = listOf(Color(0xFF1A1A1A), Color(0xFF8B0000)),
            icon = Icons.Rounded.Nightlight
        )
        "music" -> GenreStyle(
            gradientColors = listOf(Color(0xFF004D40), Color(0xFF00796B)),
            icon = Icons.Rounded.MusicNote
        )
        "mystery" -> GenreStyle(
            gradientColors = listOf(Color(0xFF1A237E), Color(0xFF3949AB)),
            icon = Icons.Rounded.Psychology
        )
        "romance" -> GenreStyle(
            gradientColors = listOf(Color(0xFF880E4F), Color(0xFFAD1457)),
            icon = Icons.Rounded.Favorite
        )
        "science fiction", "sci-fi" -> GenreStyle(
            gradientColors = listOf(Color(0xFF0D47A1), Color(0xFF1976D2)),
            icon = Icons.Rounded.RocketLaunch
        )
        "tv movie" -> GenreStyle(
            gradientColors = listOf(Color(0xFF424242), Color(0xFF616161)),
            icon = Icons.Rounded.Tv
        )
        "thriller" -> GenreStyle(
            gradientColors = listOf(Color(0xFFBF360C), Color(0xFFD84315)),
            icon = Icons.Rounded.Visibility
        )
        "war" -> GenreStyle(
            gradientColors = listOf(Color(0xFF33691E), Color(0xFF558B2F)),
            icon = Icons.Rounded.MilitaryTech
        )
        "western" -> GenreStyle(
            gradientColors = listOf(Color(0xFF4E342E), Color(0xFF6D4C41)),
            icon = Icons.Rounded.Star
        )
        else -> GenreStyle(
            gradientColors = listOf(Color(0xFFE50914), Color(0xFF1E1E1E)),
            icon = Icons.Rounded.Movie
        )
    }
}