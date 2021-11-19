package com.sample.pokedex.presentation.api.mapper

import android.net.Uri
import com.sample.pokedex.domain.entity.AbilityEntity
import com.sample.pokedex.domain.entity.PokemonEntity
import com.sample.pokedex.presentation.api.response.AbilityRemote
import com.sample.pokedex.presentation.api.response.PokemonRemote
import javax.inject.Inject

class PokemonEntityMapper @Inject constructor() {

    fun fromRemote(remote: PokemonRemote): PokemonEntity = with(remote){
        val pokemonAbilities = this.abilities?.map {
            Pair(Uri.parse(it.ability?.url.orEmpty()).lastPathSegment?.toIntOrNull() ?: 0, it.ability?.name.orEmpty())
        }?.map { (id, name)->
            AbilityEntity(
                id,
                name,
                effect = "",
                generation = ""
            )
        } ?: listOf()
        PokemonEntity(
            id = this.id,
            orderNumber = this.order ?: 0,
            name = this.name.orEmpty(),
            weight = this.weight ?: 0.0,
            height = this.height ?: 0.0,
            imageUrl = this.sprites?.other?.officialArtwork?.frontDefault.orEmpty(),
            types = this.types?.map { it.type?.name.orEmpty() } ?: listOf(),
            abilities = pokemonAbilities
        )
    }

    fun fromRemote(remote: AbilityRemote): AbilityEntity = with(remote){
        AbilityEntity(
            this.id ?: 0,
            this.name.orEmpty(),
            this.effectEntries?.firstOrNull {
                it.language?.name.orEmpty().equals("en", true)
            }?.shortEffect.orEmpty(),
            this.generation?.name.orEmpty()
        )
    }
}