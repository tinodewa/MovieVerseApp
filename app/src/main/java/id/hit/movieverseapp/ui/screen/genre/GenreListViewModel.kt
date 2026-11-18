package id.hit.movieverseapp.ui.screen.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.hit.movieverseapp.common.ResultState
import id.hit.movieverseapp.common.UiState
import id.hit.movieverseapp.data.domain.model.Genre
import id.hit.movieverseapp.data.domain.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GenreListViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Genre>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Genre>>> = _uiState.asStateFlow()

    init {
        fetchGenres()
    }

    fun fetchGenres() {
        _uiState.value = UiState.Loading
        repository.getGenres().onEach { resource ->
            _uiState.value = when (resource) {
                is ResultState.Loading -> UiState.Loading
                is ResultState.Success -> {
                    val data = resource.data
                    if (data.isNullOrEmpty()) {
                        UiState.Empty
                    } else {
                        UiState.Success(data)
                    }
                }
                is ResultState.Error -> UiState.Error(resource.message ?: "An unexpected error occurred")
            }
        }.launchIn(viewModelScope)
    }

    fun retry() {
        fetchGenres()
    }
}