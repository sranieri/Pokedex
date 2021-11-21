package com.sample.pokedex.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sample.pokedex.domain.entity.PokemonEntity
import com.sample.pokedex.domain.usecase.FetchPokemonsUseCase
import java.lang.Exception
import java.lang.IllegalStateException
import javax.inject.Inject

class PokedexPagingSource @Inject constructor(
    val fetchPokemonsUseCase: FetchPokemonsUseCase
): PagingSource<Int, PokemonEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        return runCatching {
            val offset = params.key ?: 0
            val pokemonList = fetchPokemonsUseCase.execute(FetchPokemonsUseCase.Params(offset))
            val nextOffset = offset + pokemonList.size

            if(offset == 0 && pokemonList.isEmpty()){
                // error loading
                LoadResult.Error(
                    IllegalStateException("No pokémons found")
                )
            } else if (nextOffset == offset) {
                LoadResult.Error(
                    IllegalStateException("Not found other pokémons")
                )
            } else {
                LoadResult.Page(
                    data = pokemonList,
                    prevKey = null,
                    nextKey = if (nextOffset == offset) null else nextOffset
                )
            }
        }.getOrElse {
            // error loading
            LoadResult.Error(
                IllegalStateException("Generic error")
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonEntity>): Int? {
        return null
    }
}