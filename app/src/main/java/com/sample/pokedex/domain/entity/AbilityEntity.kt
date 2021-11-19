package com.sample.pokedex.domain.entity

data class AbilityEntity(
    val id:Int,
    val name: String,
    val effect: String,
    val generation: String,
) {
    override fun toString(): String {
        return "AbilityEntity(id=$id, name='$name', effect='$effect', generation='$generation')"
    }
}
