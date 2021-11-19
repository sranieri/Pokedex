package com.sample.pokedex.presentation.api.mapper

import com.sample.pokedex.domain.entity.PokemonListEntity
import com.sample.pokedex.presentation.api.response.PokemonListRemote
import javax.inject.Inject

class PokemonListEntityMapper @Inject constructor() {

    fun fromRemote(remote: PokemonListRemote): List<PokemonListEntity> = with(remote) {
        this.results?.map {
            PokemonListEntity(it.name.orEmpty(), it.url.orEmpty())
        } ?: listOf()
    }
}