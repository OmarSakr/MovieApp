package com.example.whitehelmetcodingchallenge.presentation.details

sealed class MovieDetailsIntent {
    data class LoadMovieDetails(val movieId: Int) : MovieDetailsIntent()
}