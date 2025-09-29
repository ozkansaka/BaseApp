package com.ragnar.baseapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ragnar.baseapp.ui.screen.detail.DetailScreen
import com.ragnar.baseapp.ui.screen.home.HomeScreen
import com.ragnar.baseapp.ui.screen.home.HomeViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail?title={title}&description={description}") {
        fun createRoute(title: String, description: String) = "detail?title={$title}&description={$description}"
    }
}


@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(navController = navController, homeViewModel = homeViewModel)
        }
        composable(
            route = Screen.Detail.route, arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    nullable = true
                }, navArgument("description") {
                    type = NavType.StringType
                    nullable = true
                }
            )) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            val description = backStackEntry.arguments?.getString("description")

            DetailScreen(navController = navController, title = title, description = description)
        }
    }
}


