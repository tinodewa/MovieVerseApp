package id.hit.movieverseapp.data.sources.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoDto(
    @SerialName("id")
    var id: String? = null,
    @SerialName("key")
    var key: String? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("site")
    var site: String? = null,
    @SerialName("type")
    var type: String? = null,
)

@Serializable
data class VideoListResponseDto(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("results")
    var results: List<VideoDto>? = null,
)
