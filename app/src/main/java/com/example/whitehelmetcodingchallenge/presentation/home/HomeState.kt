package com.example.whitehelmetcodingchallenge.presentation.home

import com.example.whitehelmetcodingchallenge.domain.model.Movie

data class HomeState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val currentPage: Int = 1,
    val searchQuery: String = "",
    val isSearching: Boolean = false
)