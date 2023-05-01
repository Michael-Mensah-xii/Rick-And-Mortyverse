package com.example.rickandmortyy.screens.detail

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.rickandmortyy.ui.theme.topAppBarBackgroundColor

@Composable
fun DetailTopBar(
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        title = {},
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
    )
}

@Composable
@Preview
fun DetailTopBarPreview() {
    DetailTopBar {}
}