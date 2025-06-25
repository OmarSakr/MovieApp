package com.example.whitehelmetcodingchallenge.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val genres: List<String>,
    val isAdult: Boolean = false,
    val popularity: Double = 0.0,
    val voteCount: Int = 0
) {
    val fullPosterUrl: String
        get() = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: ""
}