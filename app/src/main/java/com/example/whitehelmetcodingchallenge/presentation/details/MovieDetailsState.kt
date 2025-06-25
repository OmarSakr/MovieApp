package com.example.whitehelmetcodingchallenge.presentation.details

import com.example.whitehelmetcodingchallenge.domain.model.Movie

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val error: String? = null
)