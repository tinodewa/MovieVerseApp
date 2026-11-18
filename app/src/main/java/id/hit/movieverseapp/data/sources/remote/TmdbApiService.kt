package id.hit.movieverseapp.data.sources.remote

import id.hit.movieverseapp.data.sources.remote.dto.GenreListResponseDto
import id.hit.movieverseapp.data.sources.remote.dto.MovieDetailDto
import id.hit.movieverseapp.data.sources.remote.dto.MovieListResponseDto
import id.hit.movieverseapp.data.sources.remote.dto.ReviewListResponseDto
import id.hit.movieverseapp.data.sources.remote.dto.VideoListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreListResponseDto

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genreId: String? = null,
        @Query("page") page: Int = 1
    ): MovieListResponseDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailDto

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailers(
        @Path("movie_id") movieId: Int
    ): VideoListResponseDto

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int = 1
    ): ReviewListResponseDto
}