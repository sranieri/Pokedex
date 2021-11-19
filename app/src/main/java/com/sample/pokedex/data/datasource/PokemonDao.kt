package com.sample.pokedex.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.pokedex.presentation.model.Pokemon
import com.sample.pokedex.presentation.model.PokemonAbility
import com.sample.pokedex.presentation.model.PokemonWithAbilities

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<Pokemon>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbilities(abilities: List<PokemonAbility>)

    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun getPokemonById(id: Int): PokemonWithAbilities?

    @Query("SELECT * FROM pokemon LIMIT 20 OFFSET :offset")
    suspend fun getPokemons(offset: Int): List<PokemonWithAbilities>

    @Query("DELETE FROM pokemon")
    suspend fun clearAll()
}