package com.sample.pokedex.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.pokedex.presentation.model.Pokemon
import com.sample.pokedex.presentation.model.PokemonAbility

@Dao
interface PokemonAbilityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(abilities: List<PokemonAbility>)

    @Query("SELECT * FROM ability WHERE id = :id")
    suspend fun getAbility(id: Int): PokemonAbility?

    @Query("DELETE FROM ability")
    suspend fun clearAll()
}