package id.hit.movieverseapp.ui.screen.discover

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.hit.movieverseapp.data.domain.model.Movie
import id.hit.movieverseapp.data.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val initialGenreId: Int? = savedStateHandle.get<Int>("genreId")
    private val initialGenreName: String? = savedStateHandle.get<String>("genreName")

    private val _genreId = MutableStateFlow<Int?>(initialGenreId)
    val genreId: StateFlow<Int?> = _genreId.asStateFlow()

    private val _genreName = MutableStateFlow<String>(initialGenreName ?: "")
    val genreName: StateFlow<String> = _genreName.asStateFlow()

    val movies: Flow<PagingData<Movie>> = _genreId
        .filterNotNull()
        .flatMapLatest { id ->
            movieRepository.getMoviesByGenreId(id)
        }
        .cachedIn(viewModelScope)

    fun setGenre(id: Int, name: String = "") {
        _genreId.value = id
        if (name.isNotEmpty()) {
            _genreName.value = name
        }
    }
}
