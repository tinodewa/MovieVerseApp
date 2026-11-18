package id.hit.movieverseapp.data.domain.repository

import androidx.paging.PagingData
import id.hit.movieverseapp.common.ResultState
import id.hit.movieverseapp.data.domain.model.Genre
import id.hit.movieverseapp.data.domain.model.Movie
import id.hit.movieverseapp.data.domain.model.MovieDetail
import id.hit.movieverseapp.data.domain.model.Review
import id.hit.movieverseapp.data.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getGenres(): Flow<ResultState<List<Genre>>>
    fun getMoviesByGenreId(genreId: Int): Flow<PagingData<Movie>>
    fun getDetailsByMovieId(movieId: Int): Flow<ResultState<MovieDetail>>
    fun getTrailersByMovieId(movieId: Int): Flow<ResultState<List<Video>>>
    fun getReviewsByMovieId(movieId: Int): Flow<PagingData<Review>>
}
