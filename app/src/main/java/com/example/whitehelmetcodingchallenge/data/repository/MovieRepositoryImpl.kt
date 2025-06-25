package com.example.whitehelmetcodingchallenge.data.repository

import com.example.whitehelmetcodingchallenge.data.api.MovieApi
import com.example.whitehelmetcodingchallenge.data.dto.MovieDto
import com.example.whitehelmetcodingchallenge.domain.model.Movie
import com.example.whitehelmetcodingchallenge.domain.repository.MovieRepository
import com.example.whitehelmetcodingchallenge.domain.util.Result


class MovieRepositoryImpl(private val movieApi: MovieApi) : MovieRepository {
    override suspend fun getLatestMovies(page: Int): Result<List<Movie>> {
        return try {
            val response = movieApi.getLatestMovies(page)
            if (response.isSuccessful) {
                Result.Success(response.body()?.results?.map { it.toMovie() } ?: emptyList())
            } else {
                Result.Error(Exception("Failed to fetch movies"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
    
    override suspend fun searchMovies(query: String, page: Int): Result<List<Movie>> {
        return try {
            val response = movieApi.searchMovies(query, page)
            if (response.isSuccessful) {
                Result.Success(response.body()?.results?.map { it.toMovie() } ?: emptyList())
            } else {
                Result.Error(Exception("Failed to search movies"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
    
    override suspend fun getMovieDetails(movieId: Int): Result<Movie> {
        return try {
            val response = movieApi.getMovieDetails(movieId)
            if (response.isSuccessful) {
                Result.Success(response.body()!!.toMovie())
            } else {
                Result.Error(Exception("Failed to fetch movie details"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private fun MovieDto.toMovie(): Movie {
        return Movie(
            id = id,
            title = title,
            originalTitle = originalTitle,
            overview = overview,
            posterPath = posterPath,
            backdropPath = backdropPath,
            releaseDate = releaseDate ?: "", // Handle null release date
            voteAverage = voteAverage,
            genres = genreIds?.map { genreIdToName(it) } ?: emptyList(),
            isAdult = adult,
            popularity = popularity,
            voteCount = voteCount
        )
    }

    private fun genreIdToName(genreId: Int): String {
        return when (genreId) {
            28 -> "Action"
            12 -> "Adventure"
            16 -> "Animation"
            35 -> "Comedy"
            80 -> "Crime"
            99 -> "Documentary"
            18 -> "Drama"
            10751 -> "Family"
            14 -> "Fantasy"
            36 -> "History"
            27 -> "Horror"
            10402 -> "Music"
            9648 -> "Mystery"
            10749 -> "Romance"
            878 -> "Science Fiction"
            10770 -> "TV Movie"
            53 -> "Thriller"
            10752 -> "War"
            37 -> "Western"
            else -> "Unknown"
        }
    }
}
