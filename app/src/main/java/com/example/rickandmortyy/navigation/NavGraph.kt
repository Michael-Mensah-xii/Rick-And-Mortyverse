package com.example.rickandmortyy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.example.rickandmortyy.screens.common.ListContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.rickandmortyy.screens.detail.DetailScreen
import com.example.rickandmortyy.screens.home.HomeScreen
import com.example.rickandmortyy.screens.search.SearchScreen

@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }


        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            // Retrieve the characterId argument from the NavBackStackEntry
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: -1

            DetailScreen(characterId = characterId, navController = navController)
        }

    }
}
