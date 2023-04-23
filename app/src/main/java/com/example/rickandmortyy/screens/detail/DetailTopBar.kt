package com.example.rickandmortyy.screens.detail

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.rickandmortyy.screens.home.HomeTopBar
import com.example.rickandmortyy.ui.theme.topAppBarBackgroundColor
import com.example.rickandmortyy.ui.theme.topAppBarContentColor

@Composable
fun DetailTopBar(
    title: String,
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
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
    )
}

@Composable
@Preview
fun DetailTopBarPreview() {
    DetailTopBar(title = "Rick Sanchez") {}
}