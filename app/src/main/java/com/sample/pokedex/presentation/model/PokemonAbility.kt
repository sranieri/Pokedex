package com.sample.pokedex.presentation.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "ability")
data class PokemonAbility(
    @PrimaryKey
    val id: Int,
    val name: String,
    val effect: String,
    val generation: String
)
