package id.hit.movieverseapp.data.sources.remote.mapper

import id.hit.movieverseapp.data.domain.model.Video
import id.hit.movieverseapp.data.sources.remote.dto.VideoDto
import kotlin.text.orEmpty

fun VideoDto.toDomain(): Video {
    return Video(
        id = id.orEmpty(),
        key = key.orEmpty(),
        name = name.orEmpty(),
        site = site.orEmpty(),
        type = type.orEmpty()
    )
}