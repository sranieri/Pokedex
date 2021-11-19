package com.sample.pokedex.presentation.ui.detail

import com.sample.pokedex.domain.entity.PokemonEntity
import io.uniflow.core.flow.data.UIState

sealed class PokemonState: UIState(){
    data class PokemonInfo(val pokemon: PokemonEntity): PokemonState()
}
