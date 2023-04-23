package com.example.rickandmortyy.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmortyy.R
import coil.request.ImageRequest
import com.example.rickandmortyy.model.Character
import com.example.rickandmortyy.model.CharacterLocation
import com.example.rickandmortyy.model.CharacterOrigin

@Composable
fun DetailContent(character: Character) {

   // val context = LocalContext.current
    val painter = rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current)
        .data(data = character.imageUrl).apply(block = fun ImageRequest.Builder.() {
            crossfade(true)
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_placeholder)
        }).build())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = character.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Text(
            text = "Name: ${character.name}",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
        )
        Text(
            text = "Status: ${character.status}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )
        Text(
            text = "Species: ${character.species}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )
        Text(
            text = "Gender: ${character.gender}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )
        Text(
            text = "Origin: ${character.origin.name}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )
        Text(
            text = "Location: ${character.location.name}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )
    }
}


@Preview
@Composable
fun CharacterDetailsContentPreview() {
    val character = Character(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        gender = "Male",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        origin = CharacterOrigin("Earth", "Dimension C-137"),
        location = CharacterLocation("Earth", "Dimension C-137"),
        episode = listOf("1","2","3")
    )
    DetailContent(character = character)

}



