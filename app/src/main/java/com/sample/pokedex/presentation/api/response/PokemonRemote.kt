package com.sample.pokedex.presentation.api.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonRemote(
    @Json(name = "abilities")
    val abilities: List<Ability>?,
    @Json(name = "base_experience")
    val baseExperience: Int?,
    @Json(name = "forms")
    val forms: List<Value>?,
    @Json(name = "height")
    val height: Double?,
    @Json(name = "held_items")
    val heldItems: List<Any>?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "is_default")
    val isDefault: Boolean?,
    @Json(name = "location_area_encounters")
    val locationAreaEncounters: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "order")
    val order: Int?,
    @Json(name = "past_types")
    val pastTypes: List<Any>?,
    @Json(name = "species")
    val species: Value?,
    @Json(name = "sprites")
    val sprites: Sprites?,
    @Json(name = "stats")
    val stats: List<Stat>?,
    @Json(name = "types")
    val types: List<Type>?,
    @Json(name = "weight")
    val weight: Double?
)