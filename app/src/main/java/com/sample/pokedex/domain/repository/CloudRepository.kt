package com.sample.pokedex.domain.repository

import androidx.paging.PagingData
import com.sample.pokedex.domain.entity.AbilityEntity
import com.sample.pokedex.domain.entity.PokemonEntity
import com.sample.pokedex.domain.entity.PokemonListEntity
import kotlinx.coroutines.flow.Flow

interface CloudRepository {

    suspend fun getPokemon(id: Int): PokemonEntity

    suspend fun getAbility(id: Int): AbilityEntity

    suspend fun getPokemonFromDb(id: Int): PokemonEntity?

    suspend fun getPokemonFromUrl(url: String): PokemonEntity

    suspend fun storeAllPokemons(list: List<PokemonEntity>)

    suspend fun getPokemonsFromDb(offset: Int): List<PokemonEntity>

    suspend fun getPokemons(offset: Int): List<PokemonListEntity>
}