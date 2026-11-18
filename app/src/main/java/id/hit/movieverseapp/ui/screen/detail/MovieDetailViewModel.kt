package id.hit.movieverseapp.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.hit.movieverseapp.common.ResultState
import id.hit.movieverseapp.data.domain.model.MovieDetail
import id.hit.movieverseapp.data.domain.model.Review
import id.hit.movieverseapp.data.domain.model.Video
import id.hit.movieverseapp.data.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface DetailUiState {
    object Loading : DetailUiState
    data class Success(
        val movieDetail: MovieDetail,
        val trailers: List<Video>
    ) : DetailUiState
    data class Error(val message: String) : DetailUiState
}

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val initialMovieId: Int? = savedStateHandle.get<Int>("movieId")
    private val _movieId = MutableStateFlow<Int?>(initialMovieId)
    val movieId: StateFlow<Int?> = _movieId.asStateFlow()

    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()

    val reviews: Flow<PagingData<Review>> = _movieId
        .filterNotNull()
        .flatMapLatest { id ->
            movieRepository.getReviewsByMovieId(id)
        }
        .cachedIn(viewModelScope)

    init {
        initialMovieId?.let { id ->
            loadMovieDetails(id)
        }
    }

    fun setMovieId(id: Int) {
        if (_movieId.value != id) {
            _movieId.value = id
            loadMovieDetails(id)
        } else if (_detailUiState.value is DetailUiState.Loading) {
            loadMovieDetails(id)
        }
    }

    fun loadMovieDetails(id: Int? = _movieId.value) {
        val targetId = id ?: return
        viewModelScope.launch {
            _detailUiState.value = DetailUiState.Loading
            combine(
                movieRepository.getDetailsByMovieId(targetId),
                movieRepository.getTrailersByMovieId(targetId)
            ) { detailRes, trailersRes ->
                when {
                    detailRes is ResultState.Error -> {
                        DetailUiState.Error(detailRes.message ?: "Failed to load movie details")
                    }
                    trailersRes is ResultState.Error -> {
                        if (detailRes is ResultState.Success && detailRes.data != null) {
                            DetailUiState.Success(detailRes.data, emptyList())
                        } else {
                            DetailUiState.Error(trailersRes.message ?: "Failed to load movie trailers")
                        }
                    }
                    detailRes is ResultState.Success && detailRes.data != null -> {
                        val trailers = (trailersRes as? ResultState.Success)?.data ?: emptyList()
                        DetailUiState.Success(detailRes.data, trailers)
                    }
                    else -> {
                        DetailUiState.Loading
                    }
                }
            }.collect { state ->
                _detailUiState.value = state
            }
        }
    }

    fun retry() {
        _movieId.value?.let { loadMovieDetails(it) }
    }
}
