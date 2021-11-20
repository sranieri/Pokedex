package com.sample.pokedex.presentation.utils

import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import com.sample.pokedex.R
import com.sample.pokedex.domain.entity.PokemonEntity
import com.sample.pokedex.domain.entity.PokemonType

fun MaterialCardView.setColorByPokemon(pokemonEntity: PokemonEntity){
    val res = pokemonEntity.getPokemonTypes().firstOrNull()?.let {
        getColorResByType(it)
    }?: R.color.teal_200

    val color = ContextCompat.getColor(context, res)

    setCardBackgroundColor(color)
}

fun getColorResByType(type: PokemonType): Int {
    return when(type){
        PokemonType.WATER -> R.color.water_type_color
        PokemonType.FIRE -> R.color.fire_type_color
        PokemonType.ELECTRIC -> R.color.electric_type_color
        PokemonType.BUG -> R.color.bug_type_color
        PokemonType.GRASS -> R.color.grass_type_color
        PokemonType.FAIRY -> R.color.fairy_type_color
        PokemonType.ICE -> R.color.ice_type_color
        PokemonType.GHOST -> R.color.ghost_type_color
        PokemonType.FIGHTING -> R.color.fighting_type_color
        PokemonType.GROUND -> R.color.ground_type_color
        PokemonType.FLYING -> R.color.flying_type_color
        PokemonType.POISON -> R.color.poison_type_color
        PokemonType.ROCK -> R.color.rock_type_color
        PokemonType.PSYCHIC -> R.color.psychic_type_color
        PokemonType.DARK -> R.color.dark_type_color
        PokemonType.STEEL -> R.color.steel_type_color
        PokemonType.DRAGON -> R.color.dragon_type_color
        PokemonType.NORMAL -> R.color.normal_type_color
    }
}

fun getTypeDrawable(type: PokemonType) : Int{
    return when(type){
        PokemonType.WATER -> R.drawable.water
        PokemonType.FIRE -> R.drawable.fire
        PokemonType.ELECTRIC -> R.drawable.electric
        PokemonType.BUG -> R.drawable.bug
        PokemonType.GRASS -> R.drawable.bug
        PokemonType.FAIRY -> R.drawable.fairy
        PokemonType.ICE -> R.drawable.ice
        PokemonType.GHOST -> R.drawable.ghost
        PokemonType.FIGHTING -> R.drawable.fighting
        PokemonType.GROUND -> R.drawable.ground
        PokemonType.FLYING -> R.drawable.flying
        PokemonType.POISON -> R.drawable.poison
        PokemonType.ROCK -> R.drawable.rock
        PokemonType.PSYCHIC -> R.drawable.psychic
        PokemonType.DARK -> R.drawable.dark
        PokemonType.STEEL -> R.drawable.steel
        PokemonType.DRAGON -> R.drawable.dragon
        PokemonType.NORMAL -> R.drawable.normal
    }
}