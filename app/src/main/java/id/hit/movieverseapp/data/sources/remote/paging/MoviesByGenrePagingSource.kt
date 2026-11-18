package id.hit.movieverseapp.data.sources.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.hit.movieverseapp.data.domain.model.Movie
import id.hit.movieverseapp.data.sources.remote.TmdbApiService
import id.hit.movieverseapp.data.sources.remote.mapper.toDomain
import id.hit.movieverseapp.utils.toReadableErrorMessage
import kotlinx.coroutines.CancellationException

class MoviesByGenrePagingSource(
    private val apiService: TmdbApiService,
    private val genreId: Int?
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val genreIdParam = if (genreId != null && genreId != 0) genreId.toString() else null
            val response = apiService.getMoviesByGenre(genreId = genreIdParam, page = page)
            val movies = response.results?.map { it.toDomain() } ?: emptyList()
            val totalPages = response.totalPages ?: 1

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page < totalPages && movies.isNotEmpty()) page + 1 else null
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            LoadResult.Error(Exception(e.toReadableErrorMessage(), e))
        }
    }
}