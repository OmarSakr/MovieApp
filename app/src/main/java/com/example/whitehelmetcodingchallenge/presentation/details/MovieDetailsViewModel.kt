package com.example.whitehelmetcodingchallenge.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whitehelmetcodingchallenge.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.whitehelmetcodingchallenge.domain.util.Result

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MovieDetailsState())
    val state: StateFlow<MovieDetailsState> = _state
    fun handleIntent(intent: MovieDetailsIntent.LoadMovieDetails) {
        when (intent) {
            is MovieDetailsIntent.LoadMovieDetails -> loadMovieDetails(intent.movieId)
        }
    }

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val result = getMovieDetailsUseCase(movieId)) {
                is Result.Success -> {
                    _state.update { it.copy(isLoading = false, movie = result.data) }
                }
                is Result.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.exception.message) }
                }
            }
        }
    }
}