package com.sample.pokedex.presentation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey val id: Int,
    val orderNumber: Int = 0,
    val abilities: List<Int> = listOf(),
    val types: List<String> = listOf(),
    val imageUrl: String = "",
    val name: String = "",
    val height: Double = 0.0,
    val weight: Double = 0.0
)