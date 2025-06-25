package com.example.whitehelmetcodingchallenge.presentation.home

sealed class HomeIntent {
    object LoadInitialMovies : HomeIntent()
    object LoadMoreMovies : HomeIntent()
    data class SearchMovies(val query: String) : HomeIntent()
}
