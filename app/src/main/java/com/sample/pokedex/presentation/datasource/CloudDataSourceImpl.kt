package com.sample.pokedex.presentation.datasource

import com.sample.pokedex.data.datasource.CloudDataSource
import com.sample.pokedex.domain.entity.AbilityEntity
import com.sample.pokedex.domain.entity.PokemonEntity
import com.sample.pokedex.domain.entity.PokemonListEntity
import com.sample.pokedex.presentation.api.CloudApi
import com.sample.pokedex.presentation.api.mapper.PokemonEntityMapper
import com.sample.pokedex.presentation.api.mapper.PokemonListEntityMapper
import com.sample.pokedex.presentation.api.response.PokemonListRemote

class CloudDataSourceImpl(
    val api: CloudApi,
    val pokemonMapper: PokemonEntityMapper,
    val pokemonListEntityMapper: PokemonListEntityMapper
) : CloudDataSource {

    override suspend fun getPokemons(offset: Int, itemsForPage: Int): List<PokemonListEntity> {
        return pokemonListEntityMapper.fromRemote(api.getPokemons(offset, itemsForPage))
    }

    override suspend fun getPokemonList(offset: Int, itemsForPage: Int): PokemonListRemote {
        return api.getPokemons(offset, itemsForPage)
    }

    override suspend fun getPokemon(id: Int): PokemonEntity {
        return pokemonMapper.fromRemote(api.getPokemon(id))
    }

    override suspend fun getAbility(id: Int): AbilityEntity {
        return pokemonMapper.fromRemote(api.getPokemonAbility(id))
    }

    override suspend fun getPokemonFromUrl(url: String): PokemonEntity {
        return pokemonMapper.fromRemote(api.getPokemonFromUrl(url))
    }
}