package com.sample.pokedex.domain.entity

data class PokemonEntity(
    val id: Int = 0,
    val orderNumber: Int = 0,
    val abilities: List<AbilityEntity> = listOf(),
    val types: List<String> = listOf(),
    val imageUrl: String = "",
    val name: String = "",
    val height: Double = 0.0,
    val weight: Double = 0.0
)
