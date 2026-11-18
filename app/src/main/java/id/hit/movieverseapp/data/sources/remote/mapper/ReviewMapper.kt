package id.hit.movieverseapp.data.sources.remote.mapper

import id.hit.movieverseapp.data.domain.model.Review
import id.hit.movieverseapp.data.sources.remote.dto.ReviewDto
import kotlin.text.orEmpty

fun ReviewDto.toDomain(): Review {
    return Review(
        id = id.orEmpty(),
        author = author.orEmpty(),
        content = content.orEmpty(),
        createdAt = createdAt.orEmpty(),
        avatarPath = authorDetails?.avatarPath,
        rating = authorDetails?.rating
    )
}