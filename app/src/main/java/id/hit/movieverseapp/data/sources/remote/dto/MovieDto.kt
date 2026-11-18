package id.hit.movieverseapp.data.sources.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("title")
    var title: String? = null,
    @SerialName("overview")
    var overview: String? = null,
    @SerialName("poster_path")
    var posterPath: String? = null,
    @SerialName("backdrop_path")
    var backdropPath: String? = null,
    @SerialName("release_date")
    var releaseDate: String? = null,
    @SerialName("vote_average")
    var voteAverage: Double? = null,
    @SerialName("vote_count")
    var voteCount: Int? = null,
    @SerialName("genre_ids")
    val genreIds: List<Int>? = null,
)

@Serializable
data class MovieListResponseDto(
    @SerialName("page")
    var page: Int? = null,
    @SerialName("results")
    val results: List<MovieDto>? = null,
    @SerialName("total_pages")
    var totalPages: Int? = null,
    @SerialName("total_results")
    var totalResults: Int? = null,
)

@Serializable
data class MovieDetailDto(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("title")
    var title: String? = null,
    @SerialName("overview")
    var overview: String? = null,
    @SerialName("poster_path")
    var posterPath: String? = null,
    @SerialName("backdrop_path")
    var backdropPath: String? = null,
    @SerialName("release_date")
    var releaseDate: String? = null,
    @SerialName("vote_average")
    var voteAverage: Double? = null,
    @SerialName("vote_count")
    var voteCount: Int? = null,
    @SerialName("runtime")
    var runtime: Int? = null,
    @SerialName("genres")
    var genres: List<GenreDto>? = null,
    @SerialName("status")
    var status: String? = null,
    @SerialName("tagline")
    var tagline: String? = null,
)