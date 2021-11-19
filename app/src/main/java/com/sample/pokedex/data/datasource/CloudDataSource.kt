package com.sample.pokedex.data.datasource

import com.sample.pokedex.domain.entity.AbilityEntity
import com.sample.pokedex.domain.entity.PokemonEntity
import com.sample.pokedex.domain.entity.PokemonListEntity
import com.sample.pokedex.presentation.api.response.PokemonListRemote

interface CloudDataSource {

    suspend fun getPokemons(offset: Int, itemsForPage: Int): List<PokemonListEntity>

    suspend fun getPokemonList(offset: Int, itemsForPage: Int): PokemonListRemote

    suspend fun getPokemon(id: Int): PokemonEntity

    suspend fun getAbility(id: Int): AbilityEntity

    suspend fun getPokemonFromUrl(url: String): PokemonEntity
}