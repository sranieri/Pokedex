package com.sample.pokedex.presentation.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.pokedex.data.datasource.PokemonAbilityDao
import com.sample.pokedex.data.datasource.PokemonDao
import com.sample.pokedex.presentation.model.Pokemon
import com.sample.pokedex.presentation.model.PokemonAbility

@Database(entities = [Pokemon::class, PokemonAbility::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class, IntegerListConverter::class)
abstract class AppDB : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonAbilityDao(): PokemonAbilityDao
}