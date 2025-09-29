package com.ragnar.baseapp.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ragnar.baseapp.model.Popular
import com.ragnar.baseapp.ui.component.Movie
import com.ragnar.baseapp.ui.component.MovieCard
import com.ragnar.baseapp.ui.screen.home.HomeContent
import com.ragnar.baseapp.ui.screen.home.HomeViewModel

@Composable
fun DetailScreen(navController: NavHostController, title: String?, description: String?) {
    DetailContent(title = title, description = description)
}

@Composable
fun DetailContent(title: String?, description: String?) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = title ?: "")
            Text(text = description ?: "")
        }
    }
}