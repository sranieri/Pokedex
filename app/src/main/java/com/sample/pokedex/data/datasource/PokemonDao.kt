package com.sample.pokedex.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.pokedex.presentation.model.Pokemon

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<Pokemon>)

    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun getPokemonById(id: Int): Pokemon?

    @Query("SELECT * FROM pokemon LIMIT 20 OFFSET :offset")
    suspend fun getPokemons(offset: Int): List<Pokemon>

    @Query("DELETE FROM pokemon")
    suspend fun clearAll()
}