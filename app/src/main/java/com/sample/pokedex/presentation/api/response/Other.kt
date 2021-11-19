package com.sample.pokedex.presentation.api.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Other(
    @Json(name = "dream_world")
    val dreamWorld: Images?,
    @Json(name = "home")
    val home: Images?,
    @Json(name = "official-artwork")
    val officialArtwork: Images?
)