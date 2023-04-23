package com.example.rickandmortyy.screens.search

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.rickandmortyy.screens.common.ListContent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchedImages = searchViewModel.searchedImages.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            SearchWidget(
                text = searchViewModel.searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchStuff(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            ListContent(items = searchedImages, onItemClick = {})
        }
    )
}