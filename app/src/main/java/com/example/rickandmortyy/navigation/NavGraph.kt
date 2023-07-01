package com.example.rickandmortyy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.rickandmortyy.screens.detail.DetailScreen
import com.example.rickandmortyy.screens.home.HomeScreen
import com.example.rickandmortyy.screens.search.SearchScreen

@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun SetUpNavGraph() {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController, startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(actions = actions)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(actions = actions)
        }


        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            // Retrieve the characterId argument from the NavBackStackEntry
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: -1

            DetailScreen(characterId = characterId, actions = actions)
        }

    }
}

class MainActions(private val navController: NavHostController) {

    val popBackStack: () -> Unit = {
        navController.popBackStack()
    }

    val onBackPressed: () -> Unit = {
        navController.navigateUp()
    }

    val openDetailPage: (characterId: Int) -> Unit = {
        navController.navigate(Screen.Detail.createRouteWithCharacterId(it))
    }

    val openSearchScreen: () -> Unit = {
        navController.navigate(Screen.Search.route)
    }
}
