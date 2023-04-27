package com.example.rickandmortyy.screens.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.rickandmortyy.R
import com.example.rickandmortyy.model.Character
import com.example.rickandmortyy.model.CharacterLocation
import com.example.rickandmortyy.model.CharacterOrigin


@ExperimentalCoilApi
@Composable
fun ListContent(
    items: LazyPagingItems<Character>,
    onItemClick: (Character) -> Unit,
    //scrollState: LazyListState,
) {
    Log.d("Error", items.loadState.toString())
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
       // state = scrollState
    ) {

        items(
            items = items,
            key = { character ->
                character.id
            }
        ) { character ->
            character?.let {
                CharacterListItem(character = it,
                    onItemClick = onItemClick)
            }
        }
    }
}


@ExperimentalCoilApi
@Composable
fun CharacterListItem(character: Character, onItemClick: (Character) -> Unit) {

    val model = ImageRequest.Builder(LocalContext.current)

        .data(character.imageUrl)
        .crossfade(true)
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_placeholder)
        .build()

    //asynchronously loads the image and manages its lifecycle(load images only when required)
    val painter = rememberAsyncImagePainter(model)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(character) })
            .padding(16.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = "Character Image",
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = character.name,
                fontSize = MaterialTheme.typography.caption.fontSize,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = character.species,
                fontSize = MaterialTheme.typography.caption.fontSize,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
            )
            Text(
                text = "Status: ${character.status}",
                fontSize = MaterialTheme.typography.caption.fontSize,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}


@ExperimentalCoilApi
@Composable
@Preview
fun UnsplashImagePreview() {
    CharacterListItem(
        character = Character(
            id = 1,
            name = "Rick Sanchez",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            species = "Human",
            status = "Alive",
            gender = "male",
            location = CharacterLocation("", ""),
            origin = CharacterOrigin("", ""),
            episode = listOf("1", "2", "3")
        ),
        onItemClick = {}
    )
}