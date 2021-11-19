package com.sample.pokedex.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sample.pokedex.domain.entity.PokemonEntity
import com.sample.pokedex.domain.usecase.FetchPokemonsUseCase
import javax.inject.Inject

class PokedexPagingSource @Inject constructor(
    val fetchPokemonsUseCase: FetchPokemonsUseCase
): PagingSource<Int, PokemonEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        val offset = params.key ?: 0
        val pokemonList = fetchPokemonsUseCase.execute(FetchPokemonsUseCase.Params(offset))
        val nextOffset = offset + pokemonList.size

        return LoadResult.Page(
            data = pokemonList,
            prevKey = null,
            nextKey = nextOffset
        )
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonEntity>): Int? {
        return null
    }
}