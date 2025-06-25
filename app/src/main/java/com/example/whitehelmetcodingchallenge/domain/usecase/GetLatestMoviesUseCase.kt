package com.example.whitehelmetcodingchallenge.domain.usecase

import com.example.whitehelmetcodingchallenge.domain.model.Movie
import com.example.whitehelmetcodingchallenge.domain.repository.MovieRepository
import com.example.whitehelmetcodingchallenge.domain.util.Result

class GetLatestMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(page: Int): Result<List<Movie>> {
        return repository.getLatestMovies(page)
    }
}