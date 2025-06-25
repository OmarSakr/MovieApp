package com.example.whitehelmetcodingchallenge.domain.usecase

import com.example.whitehelmetcodingchallenge.domain.model.Movie
import com.example.whitehelmetcodingchallenge.domain.repository.MovieRepository
import com.example.whitehelmetcodingchallenge.domain.util.Result

class GetMovieDetailsUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Result<Movie> {
        return repository.getMovieDetails(movieId)
    }
}