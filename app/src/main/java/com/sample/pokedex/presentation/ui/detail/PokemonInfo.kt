package com.sample.pokedex.presentation.ui.detail

import com.sample.pokedex.domain.entity.PokemonEntity
import io.uniflow.core.flow.data.UIState

data class PokemonInfo(val pokemon: PokemonEntity): UIState()
