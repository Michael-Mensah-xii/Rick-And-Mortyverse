package com.example.rickandmortyy.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import coil.request.ImageRequest
import com.example.rickandmortyy.R
import com.example.rickandmortyy.model.Character
import com.example.rickandmortyy.model.CharacterLocation
import com.example.rickandmortyy.model.CharacterOrigin

@Composable
fun DetailContent(character: Character) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = character.imageUrl)
            .apply {
                crossfade(false)
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_placeholder)
            }
            .build()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            Box(
                modifier = Modifier
                    .height(450.dp)
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Image(
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = character.name,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(0.4f)
                    ) {
                        Text(
                            text = "Status",
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.secondaryVariant,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = character.status,
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Column(
                        modifier = Modifier.weight(0.4f)
                    ) {
                        Text(
                            text = "Species",
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.secondaryVariant,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = character.species,
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Column(
                        modifier = Modifier.weight(0.2f)
                    ) {
                        Text(
                            text = "Gender",
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.secondaryVariant,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = character.gender,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Origin",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = character.origin.name,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Location",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = character.location.name,
                    style = MaterialTheme.typography.body1
                )
            }
        }
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
        episode = listOf("1", "2", "3")
    )
    DetailContent(character = character)

}



