package com.sample.pokedex.presentation.api.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AbilityRemote(
    @Json(name = "effect_entries")
    val effectEntries: List<EffectEntry>?,
    @Json(name = "generation")
    val generation: Value?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)