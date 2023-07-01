package com.example.rickandmortyy.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.rickandmortyy.navigation.MainActions
import com.example.rickandmortyy.screens.common.ListContent
import com.example.rickandmortyy.ui.theme.Bar
import com.example.rickandmortyy.ui.theme.topAppBarBackgroundColor


//check
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun HomeScreen(
    characterViewModel: CharacterViewModel = hiltViewModel(),
    actions: MainActions
) {
    val getAllImages = characterViewModel.getCharacters.collectAsLazyPagingItems()

    // Track whether the initial connection failed or not
    var initialConnectionFailed by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    actions.openSearchScreen()
                }
            )
        },
        content = {
            when (getAllImages.loadState.refresh) {
                is LoadState.Loading -> {
                    // Show a progress indicator while the data is being fetched
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colors.topAppBarBackgroundColor)
                    }
                }

                is LoadState.Error -> {
                    // Show an error message with a retry button when the initial connection fails
                    initialConnectionFailed = true
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Failed to load characters",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(8.dp)
                            )
                            Button(
                                onClick = { getAllImages.retry() },
                                modifier = Modifier.padding(top = 8.dp),
                                colors = (ButtonDefaults.buttonColors(Bar)),
                            ) {
                                Text(text = "Retry")
                            }
                        }
                    }
                }

                else -> {
                    // Show the list of characters
                    ListContent(items = getAllImages, onItemClick = {
                        actions.openDetailPage(it.id)
                    })
                }
            }
        }
    )
}









