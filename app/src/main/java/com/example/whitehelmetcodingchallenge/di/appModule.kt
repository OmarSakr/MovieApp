package com.example.whitehelmetcodingchallenge.di

import com.example.whitehelmetcodingchallenge.data.api.MovieApi
import com.example.whitehelmetcodingchallenge.data.api.MovieApiClient
import com.example.whitehelmetcodingchallenge.data.repository.MovieRepositoryImpl
import com.example.whitehelmetcodingchallenge.domain.repository.MovieRepository
import com.example.whitehelmetcodingchallenge.domain.usecase.GetLatestMoviesUseCase
import com.example.whitehelmetcodingchallenge.domain.usecase.GetMovieDetailsUseCase
import com.example.whitehelmetcodingchallenge.domain.usecase.SearchMoviesUseCase
import com.example.whitehelmetcodingchallenge.presentation.details.MovieDetailsViewModel
import com.example.whitehelmetcodingchallenge.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Network
    single<MovieApi> { MovieApiClient.create() }

    // Repository
    single<MovieRepository> { MovieRepositoryImpl(get()) }

    // Use Cases
    single { GetLatestMoviesUseCase(get()) }
    single { SearchMoviesUseCase(get()) }
    single { GetMovieDetailsUseCase(get()) }

    // ViewModels
    viewModel { HomeViewModel(get(), get()) }
    viewModel { MovieDetailsViewModel(get()) }
}