package id.hit.movieverseapp.data.sources.remote.mapper

import id.hit.movieverseapp.data.domain.model.Genre
import id.hit.movieverseapp.data.sources.remote.dto.GenreDto

fun GenreDto.toDomain(): Genre {
    return Genre(
        id = id ?: 0,
        name = name.orEmpty()
    )
}

