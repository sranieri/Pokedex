package com.sample.pokedex.presentation.utils

import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import com.sample.pokedex.R
import com.sample.pokedex.domain.entity.PokemonEntity

fun MaterialCardView.setColorByPokemon(pokemonEntity: PokemonEntity){
    val res = pokemonEntity.types.firstOrNull()?.let {
        getColorResByType(it)
    }?: R.color.teal_200

    val color = ContextCompat.getColor(context, res)

    setCardBackgroundColor(color)
}

fun getColorResByType(type: String): Int {
    return when(type){
        "water" -> R.color.water_type_color
        "fire" -> R.color.fire_type_color
        "electric" -> R.color.electric_type_color
        "bug" -> R.color.bug_type_color
        "grass" -> R.color.grass_type_color
        "fairy" -> R.color.fairy_type_color
        "ice" -> R.color.ice_type_color
        "ghost" -> R.color.ghost_type_color
        "fighting" -> R.color.fighting_type_color
        "ground" -> R.color.ground_type_color
        "flying" -> R.color.flying_type_color
        "poison" -> R.color.poison_type_color
        "rock" -> R.color.rock_type_color
        "psychic" -> R.color.psychic_type_color
        "dark" -> R.color.dark_type_color
        "steel" -> R.color.steel_type_color
        "dragon" -> R.color.dragon_type_color
        "normal" -> R.color.normal_type_color
        else -> R.color.teal_200
    }
}

fun getTypeDrawable(type: String) : Int{
    return when(type){
        "water" -> R.drawable.water
        "fire" -> R.drawable.fire
        "electric" -> R.drawable.electric
        "bug" -> R.drawable.bug
        "grass" -> R.drawable.bug
        "fairy" -> R.drawable.fairy
        "ice" -> R.drawable.ice
        "ghost" -> R.drawable.ghost
        "fighting" -> R.drawable.fighting
        "ground" -> R.drawable.ground
        "flying" -> R.drawable.flying
        "poison" -> R.drawable.poison
        "rock" -> R.drawable.rock
        "psychic" -> R.drawable.psychic
        "dark" -> R.drawable.dark
        "steel" -> R.drawable.steel
        "dragon" -> R.drawable.dragon
        "normal" -> R.drawable.normal
        else -> R.drawable.normal
    }
}