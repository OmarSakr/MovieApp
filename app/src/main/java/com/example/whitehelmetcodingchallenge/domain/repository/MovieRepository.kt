package com.example.whitehelmetcodingchallenge.domain.repository

import com.example.whitehelmetcodingchallenge.domain.model.Movie
import com.example.whitehelmetcodingchallenge.domain.util.Result


interface MovieRepository {
    suspend fun getLatestMovies(page: Int): Result<List<Movie>>
    suspend fun searchMovies(query: String, page: Int): Result<List<Movie>>
    suspend fun getMovieDetails(movieId: Int): Result<Movie>
}