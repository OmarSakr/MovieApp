package com.example.whitehelmetcodingchallenge.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whitehelmetcodingchallenge.domain.usecase.GetLatestMoviesUseCase
import com.example.whitehelmetcodingchallenge.domain.usecase.SearchMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.whitehelmetcodingchallenge.domain.util.Result

class HomeViewModel(
    private val getLatestMoviesUseCase: GetLatestMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state
    
    private fun setState(update: HomeState.() -> HomeState) {
        _state.update(update)
    }
    
    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.LoadInitialMovies -> loadInitialMovies()
            HomeIntent.LoadMoreMovies -> loadMoreMovies()
            is HomeIntent.SearchMovies -> searchMovies(intent.query)
        }
    }
    
    private fun loadInitialMovies() {
        viewModelScope.launch {
            setState { copy(isLoading = true, error = null) }
            when (val result = getLatestMoviesUseCase(1)) {
                is Result.Success -> {
                    setState {
                        copy(
                            isLoading = false,
                            movies = result.data,
                            currentPage = 2,
                            endReached = result.data.isEmpty()
                        )
                    }
                }
                is Result.Error -> {
                    setState { copy(isLoading = false, error = result.exception.message) }
                }
            }
        }
    }
    
    private fun loadMoreMovies() {
        if (_state.value.isLoading || _state.value.endReached) return
        
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            val currentPage = _state.value.currentPage
            val currentMovies = _state.value.movies
            
            val result = if (_state.value.isSearching) {
                searchMoviesUseCase(_state.value.searchQuery, currentPage)
            } else {
                getLatestMoviesUseCase(currentPage)
            }
            
            when (result) {
                is Result.Success -> {
                    setState {
                        copy(
                            isLoading = false,
                            movies = currentMovies + result.data,
                            currentPage = currentPage + 1,
                            endReached = result.data.isEmpty()
                        )
                    }
                }
                is Result.Error -> {
                    setState { copy(isLoading = false, error = result.exception.message) }
                }
            }
        }
    }
    
    private fun searchMovies(query: String) {
        viewModelScope.launch {
            setState { copy(searchQuery = query, isSearching = query.isNotEmpty()) }
            if (query.isEmpty()) {
                loadInitialMovies()
            } else {
                setState { copy(isLoading = true, error = null) }
                when (val result = searchMoviesUseCase(query, 1)) {
                    is Result.Success -> {
                        setState {
                            copy(
                                isLoading = false,
                                movies = result.data,
                                currentPage = 2,
                                endReached = result.data.isEmpty()
                            )
                        }
                    }
                    is Result.Error -> {
                        setState { copy(isLoading = false, error = result.exception.message) }
                    }
                }
            }
        }
    }
}