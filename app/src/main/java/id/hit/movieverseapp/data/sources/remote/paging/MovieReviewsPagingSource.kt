package id.hit.movieverseapp.data.sources.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.hit.movieverseapp.data.domain.model.Review
import id.hit.movieverseapp.data.sources.remote.TmdbApiService
import id.hit.movieverseapp.data.sources.remote.mapper.toDomain
import id.hit.movieverseapp.utils.toReadableErrorMessage
import kotlinx.coroutines.CancellationException

class MovieReviewsPagingSource(
    private val apiService: TmdbApiService,
    private val movieId: Int
) : PagingSource<Int, Review>() {

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getMovieReviews(movieId = movieId, page = page)
            val reviews = response.results?.map { it.toDomain() } ?: emptyList()
            val totalPages = (response.totalPages ?: 1).coerceAtMost(500)

            LoadResult.Page(
                data = reviews,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page < totalPages && reviews.isNotEmpty()) page + 1 else null
            )
        }catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            LoadResult.Error(Exception(e.toReadableErrorMessage(), e))
        }
    }
}