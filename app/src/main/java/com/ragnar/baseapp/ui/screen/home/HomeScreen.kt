package com.ragnar.baseapp.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import com.ragnar.baseapp.model.Popular
import com.ragnar.baseapp.navigation.Screen
import com.ragnar.baseapp.ui.component.Movie
import com.ragnar.baseapp.ui.component.MovieCard
import com.ragnar.baseapp.ui.theme.BaseAppTheme

@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeViewModel) {
    val homeUiState = homeViewModel.homeUiState
    var popular by remember { mutableStateOf<Popular?>(null) }

    LaunchedEffect(key1 = homeUiState) {
        homeUiState.error?.let {
            homeUiState.error = null
        }

        homeUiState.popular?.let {
            popular = it
        }
    }

    HomeContent(popular = popular, onClickDetail = { title, description ->
        navController.navigate(Screen.Detail.createRoute(title, description))
    })
}

@Composable
fun HomeContent(popular: Popular?, onClickDetail: (String, String) -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    items = popular?.results ?: listOf(),
                    itemContent = { movie ->
                        Column {
                            MovieCard(
                                movie = Movie(
                                    id = movie?.id,
                                    title = movie?.title,
                                    description = movie?.overview,
                                    image = movie?.backdropPath,
                                    imdb = movie?.voteAverage,
                                    poster = movie?.posterPath
                                ),
                                imageType = ""
                            ) { id ->
                                onClickDetail(movie?.title ?: "", movie?.overview ?: "")
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeContentPreview() {
    BaseAppTheme {
        HomeContent(null, { _, _ -> })
    }
}