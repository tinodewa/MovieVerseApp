package id.hit.movieverseapp.data.sources.remote.mapper

import id.hit.movieverseapp.data.domain.model.Movie
import id.hit.movieverseapp.data.domain.model.MovieDetail
import id.hit.movieverseapp.data.sources.remote.dto.MovieDetailDto
import id.hit.movieverseapp.data.sources.remote.dto.MovieDto

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id ?: 0,
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate.orEmpty(),
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        genreIds = genreIds ?: emptyList()
    )
}

fun MovieDetailDto.toDomain(): MovieDetail {
    return MovieDetail(
        id = id ?: 0,
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate.orEmpty(),
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        runtime = runtime,
        genres = genres?.map { it.toDomain() } ?: emptyList(),
        status = status,
        tagline = tagline
    )
}