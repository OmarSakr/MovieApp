package com.example.whitehelmetcodingchallenge.domain.model

data class MoviesResponse(
    val page: Int,
    val movies: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)