package com.example.rickandmortyy.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyy.model.Character
import com.example.rickandmortyy.remote.RickAndMortyApiService


// loading and refreshing pages of data from the API and providing it to the "Paging" library.
class CharacterPagingSource(
    private val rickAndMortyApiService: RickAndMortyApiService,
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        //get current page value
        val currentPage = params.key ?: 1
        return try {
            //send get request to searchImages endpoint in API
            val response = rickAndMortyApiService.getCharacters(page = currentPage)

            //check if image list is empty
            val endOfPaginationReached = response.results.isEmpty()

            if (response.results.isNotEmpty()) {
                LoadResult.Page(
                    data = response.results,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int,Character>): Int? {
        return state.anchorPosition
    }
}