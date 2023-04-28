package com.example.rickandmortyy.screens.detail

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.rickandmortyy.ui.theme.topAppBarBackgroundColor


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoilApi
@Composable
fun DetailScreen(
    navController: NavHostController,
    characterId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val characterDetails by viewModel.characterDetails.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(key1 = characterId) {
        viewModel.getCharacterDetails(characterId)
    }

    AnimatedVisibility(
        visible = characterDetails != null || errorMessage != null || isLoading,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(300)
        ) + fadeIn(animationSpec = tween(300)),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(300)
        ) + fadeOut(animationSpec = tween(300))
    ) {
        Scaffold(
            topBar = {
                DetailTopBar(
                    title = characterDetails?.name ?: "",
                    onBackPressed = { navController.popBackStack() }
                )
            },
            content = {
                if (isLoading) {
                    // show a loading indicator while the details are being fetched
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colors.topAppBarBackgroundColor)
                    }
                } else if (errorMessage != null) {
                    // show an error message with a retry button
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = errorMessage!!,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                        Button(
                            onClick = { viewModel.retry(characterId) },
                            modifier = Modifier.padding(top = 72.dp),
                            colors = (ButtonDefaults.buttonColors(MaterialTheme.colors.topAppBarBackgroundColor)),
                        ) {
                            Text(text = "Retry")
                        }
                    }
                } else if (characterDetails != null) {
                    DetailContent(character = characterDetails!!)
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No details available for this character.",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        )
    }
}
