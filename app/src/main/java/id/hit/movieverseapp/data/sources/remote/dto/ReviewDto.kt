package id.hit.movieverseapp.data.sources.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDetailsDto(
    @SerialName("name")
    var name: String? = null,
    @SerialName("username")
    var username: String? = null,
    @SerialName("avatar_path")
    var avatarPath: String? = null,
    @SerialName("rating")
    var rating: Double? = null,
)


@Serializable
data class ReviewDto(
    @SerialName("id")
    var id: String? = null,
    @SerialName("author")
    var author: String? = null,
    @SerialName("author_details")
    var authorDetails: AuthorDetailsDto? = null,
    @SerialName("content")
    var content: String? = null,
    @SerialName("created_at")
    var createdAt: String? = null,
)


@Serializable
data class ReviewListResponseDto(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("page")
    var page: Int? = null,
    @SerialName("results")
    val results: List<ReviewDto>?,
    @SerialName("total_pages")
    var totalPages: Int? = null,
    @SerialName("total_results")
    var totalResults: Int? = null,
)