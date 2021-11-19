package com.sample.pokedex.presentation.api.response


import com.sample.pokedex.presentation.api.response.Value
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EffectEntry(
    @Json(name = "effect")
    val effect: String?,
    @Json(name = "language")
    val language: Value?,
    @Json(name = "short_effect")
    val shortEffect: String?
)