package com.sample.pokedex.presentation.api.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ability(
    @Json(name = "ability")
    val ability: Value?,
    @Json(name = "is_hidden")
    val isHidden: Boolean?,
    @Json(name = "slot")
    val slot: Int?
)