package com.sample.pokedex.presentation.model

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonWithAbilities(
    @Embedded
    val pokemon: Pokemon,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val abilities: List<PokemonAbility>
)
