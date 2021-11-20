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
) {
    fun getPokemonTypes(): List<PokemonType>{
        return types.map {pokemonType ->
            PokemonType.values().firstOrNull { it.type.equals(pokemonType, true) } ?: PokemonType.NORMAL
        }
    }
}

enum class PokemonType(val type: String){
    WATER("water"),
    FIRE("fire"),
    ELECTRIC("electric"),
    BUG("bug"),
    GRASS("grass"),
    FAIRY("fairy"),
    ICE("ice"),
    GHOST("ghost"),
    FIGHTING("fighting"),
    GROUND("ground"),
    FLYING("flying"),
    POISON("poison"),
    ROCK("rock"),
    PSYCHIC("psychic"),
    DARK("dark"),
    STEEL("steel"),
    DRAGON("dragon"),
    NORMAL("normal")
}
