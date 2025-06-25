package com.example.whitehelmetcodingchallenge.data.api

import com.example.whitehelmetcodingchallenge.BuildConfig
import com.example.whitehelmetcodingchallenge.data.dto.MovieDto
import com.example.whitehelmetcodingchallenge.data.dto.MoviesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("trending/movie/week")
    suspend fun getLatestMovies(
        @Query("page") page: Int,
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en",
    ): Response<MoviesResponseDto>
    
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en",
    ): Response<MoviesResponseDto>
    
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en",
    ): Response<MovieDto>
}
