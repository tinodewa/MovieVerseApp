package id.hit.movieverseapp.data.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val genreIds: List<Int>
) {
    val posterUrl: String?
        get() = posterPath?.let {
            "https://image.tmdb.org/t/p/w500$it"
        }

    val backdropUrl: String?
        get() = backdropPath?.let {
            "https://image.tmdb.org/t/p/original$it"
        }
}

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val runtime: Int?,
    val genres: List<Genre>,
    val status: String?,
    val tagline: String?
) {
    val posterUrl: String?
        get() = posterPath?.let {
            "https://image.tmdb.org/t/p/w500$it"
        }

    val backdropUrl: String?
        get() = backdropPath?.let {
            "https://image.tmdb.org/t/p/original$it"
        }
}