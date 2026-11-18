package id.hit.movieverseapp.data.sources.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import id.hit.movieverseapp.common.ResultState
import id.hit.movieverseapp.data.domain.model.Genre
import id.hit.movieverseapp.data.domain.model.Movie
import id.hit.movieverseapp.data.domain.model.MovieDetail
import id.hit.movieverseapp.data.domain.model.Review
import id.hit.movieverseapp.data.domain.model.Video
import id.hit.movieverseapp.data.domain.repository.MovieRepository
import id.hit.movieverseapp.data.sources.remote.mapper.toDomain
import id.hit.movieverseapp.data.sources.remote.paging.MovieReviewsPagingSource
import id.hit.movieverseapp.data.sources.remote.paging.MoviesByGenrePagingSource
import id.hit.movieverseapp.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val apiService: TmdbApiService
) : MovieRepository {
    override fun getGenres(): Flow<ResultState<List<Genre>>> = safeApiCall {
        val response = apiService.getGenres()
        response.genres?.map { it.toDomain() } ?: emptyList()
    }

    override fun getMoviesByGenreId(genreId: Int): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesByGenrePagingSource(apiService, genreId) }
        ).flow
    }

    override fun getDetailsByMovieId(movieId: Int): Flow<ResultState<MovieDetail>> = safeApiCall {
        val response = apiService.getMovieDetails(movieId)
        response.toDomain()
    }

    override fun getTrailersByMovieId(movieId: Int): Flow<ResultState<List<Video>>> = safeApiCall {
        val response = apiService.getMovieTrailers(movieId)
        response.results?.map { it.toDomain() } ?: emptyList()
    }

    override fun getReviewsByMovieId(movieId: Int): Flow<PagingData<Review>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MovieReviewsPagingSource(apiService, movieId) }
        ).flow
    }
}