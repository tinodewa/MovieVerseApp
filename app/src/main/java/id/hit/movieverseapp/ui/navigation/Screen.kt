package id.hit.movieverseapp.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Screen : NavKey {
    @Serializable
    data object GenreList : Screen

    @Serializable
    data class DiscoverMovies(val genreId: Int, val genreName: String) : Screen

    @Serializable
    data class MovieDetail(val movieId: Int) : Screen
}