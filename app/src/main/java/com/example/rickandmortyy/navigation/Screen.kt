package com.example.rickandmortyy.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Search : Screen("search_screen")

    object Detail : Screen("detail_screen/{characterId}") // new route with characterId parameter

    // add a function to create the route with the characterId parameter
    fun createRouteWithCharacterId(characterId: Int): String {
        return "detail_screen/$characterId"
    }}




