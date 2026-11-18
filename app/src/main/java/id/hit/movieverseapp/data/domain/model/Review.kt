package id.hit.movieverseapp.data.domain.model

data class Review(
    val id: String,
    val author: String,
    val content: String,
    val createdAt: String,
    val avatarPath: String?,
    val rating: Double?
) {
    val avatarUrl: String?
        get() = avatarPath?.let { path ->
            when {
                path.startsWith("/https") -> path.substring(1)
                path.startsWith("https") -> path
                else -> "https://image.tmdb.org/t/p/w185$path"
            }
        }
}
