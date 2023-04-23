package com.example.rickandmortyy.screens.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.rickandmortyy.model.Character


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoilApi
@Composable
fun DetailScreen(
    navController: NavHostController,
    character: Character,
    viewModel: DetailViewModel = hiltViewModel()
) {
    var characterDetails by remember { mutableStateOf<Character?>(null) }

    // log the character object to the console
    Log.d("DetailScreen", "Character: $character")

    LaunchedEffect(key1 = character.id) {
        viewModel.getCharacterDetails(character.id)
    }

    Scaffold(
        topBar = {
            DetailTopBar(
                title = character.name,
                onBackPressed = { navController.popBackStack() }
            )
        },
        content = {
            when {
                viewModel.isLoading.value == true -> {
                    // show a loading indicator while the details are being fetched
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                viewModel.errorMessage.value != null -> {
                    // show an error message with a retry button
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = viewModel.errorMessage.value!!,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                        Button(
                            onClick = { viewModel.retry(character.id) },
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text(text = "Retry")
                        }
                    }
                }
                else -> {
                    viewModel.characterDetails.value?.let { details ->
                        DetailContent(character = details)
                    }
                }
            }
        }
    )
}
