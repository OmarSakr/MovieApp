package com.example.whitehelmetcodingchallenge.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.example.whitehelmetcodingchallenge.presentation.common.ErrorMessage
import com.example.whitehelmetcodingchallenge.presentation.common.FullScreenLoading
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onMovieClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val lazyListState = rememberLazyListState()
    
    LaunchedEffect(Unit) {
        viewModel.handleIntent(HomeIntent.LoadInitialMovies)
    }
    
    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo }
            .map { visibleItems ->
                if (visibleItems.isNotEmpty()) {
                    val lastVisibleItem = visibleItems.last()
                    lastVisibleItem.index >= lazyListState.layoutInfo.totalItemsCount - 5
                } else {
                    false
                }
            }
            .distinctUntilChanged()
            .collect { shouldLoadMore ->
                if (shouldLoadMore) {
                    viewModel.handleIntent(HomeIntent.LoadMoreMovies)
                }
            }
    }
    
    Column(modifier = Modifier.fillMaxSize()) {
        MovieSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = { query ->
                viewModel.handleIntent(HomeIntent.SearchMovies(query))
            }
        )

        when {
            state.isLoading && state.movies.isEmpty() -> FullScreenLoading()
            state.error != null -> ErrorMessage(state.error!!) {
                viewModel.handleIntent(HomeIntent.LoadInitialMovies)
            }
            else -> MovieList(
                movies = state.movies,
                lazyListState = lazyListState,
                isLoading = state.isLoading,
                onMovieClick = onMovieClick
            )
        }
    }
}