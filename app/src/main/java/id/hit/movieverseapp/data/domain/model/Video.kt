package id.hit.movieverseapp.data.domain.model

data class Video(
    val id: String,
    val key: String,
    val name: String,
    val site: String,
    val type: String
) {
    val youtubeUrl: String
        get() = "https://www.youtube.com/watch?v=$key"

    val thumbnailUrl: String
        get() = "https://img.youtube.com/vi/$key/hqdefault.jpg"
}