package com.ragnar.baseapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ragnar.baseapp.ui.theme.Blue
import com.ragnar.baseapp.ui.theme.Shapes
import com.ragnar.baseapp.ui.theme.Transparent
import com.ragnar.baseapp.ui.theme.White

data class Movie(
    val id: Int?,
    val title: String?,
    val description: String?,
    val imdb: Double?,
    val image: String?,
    val poster: String?,
)


@Composable
fun MovieCard(
    movie: Movie,
    imageType: String,
    onClick: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(bottom = 8.dp)
            .clickable(onClick = {
                movie.id?.let { onClick(it) }
            }),
        shape = Shapes.medium,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Blue),
                painter = rememberAsyncImagePainter(
                    model = if (imageType == "poster") "https://image.tmdb.org/t/p/w500${movie.poster}" else "https://image.tmdb.org/t/p/w500${movie.image}"
                ),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
            )
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopEnd),
                shape = Shapes.large,
                colors = CardDefaults.cardColors(containerColor = Transparent)
            ) {
                Text(
                    text = movie.imdb.toString(),
                    color = White,
                    modifier = Modifier
                        .padding(5.dp),
                )
            }
        }
    }
}

