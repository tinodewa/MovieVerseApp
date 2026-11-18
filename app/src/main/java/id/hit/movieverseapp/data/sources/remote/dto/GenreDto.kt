package id.hit.movieverseapp.data.sources.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null,
)

@Serializable
data class GenreListResponseDto(
    @SerialName("genres")
    val genres: List<GenreDto>?,
)