package com.sample.pokedex.presentation.api

import com.sample.pokedex.presentation.api.response.AbilityRemote
import com.sample.pokedex.presentation.api.response.PokemonListRemote
import com.sample.pokedex.presentation.api.response.PokemonRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface CloudApi {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonListRemote

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): PokemonRemote

    @GET
    suspend fun getPokemonFromUrl(@Url url: String): PokemonRemote

    @GET("ability/{id}")
    suspend fun getPokemonAbility(@Path("id") id: Int): AbilityRemote
}